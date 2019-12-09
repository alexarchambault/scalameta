package scala.meta.tests.cli

import java.io._
import java.nio.charset.StandardCharsets._
import java.nio.file._
import org.scalatest.FunSuite
import scala.collection.JavaConverters._
import scala.meta.cli._
import scala.meta.testkit._
import scala.meta.tests.metacp._

class CliSuite extends FunSuite with DiffAssertions {
  object HelloWorld {
    val sourceroot = Files.createTempDirectory("sourceroot_")
    val scalaFile = sourceroot.resolve("HelloWorld.scala")
    Files.write(
      scalaFile,
      """
      |object HelloWorld {
      |  def main(args: Array[String]): Unit = {
      |    println("hello world")
      |  }
      |}
    """.trim.stripMargin.getBytes(UTF_8)
    )
    val target = Files.createTempDirectory("target_")
    val semanticdb = target.resolve("META-INF/semanticdb/HelloWorld.scala.semanticdb")
  }

  test("metac " + HelloWorld.scalaFile) {
    import HelloWorld._
    val (success, out, err) = CliSuite.communicate { (out, err) =>
      val scalacArgs = List(
        "-cp",
        Library.scalaLibrary.classpath().syntax,
        "-P:semanticdb:sourceroot:" + sourceroot.toString,
        "-P:semanticdb:text:on",
        "-d",
        target.toString,
        scalaFile.toString
      )
      val settings = scala.meta.metac.Settings().withScalacArgs(scalacArgs)
      val reporter = Reporter()
      Metac.process(settings, reporter)
    }
    assert(success)
    assert(out.isEmpty)
    assert(Files.exists(semanticdb))
  }

  test("metap " + HelloWorld.scalaFile) {
    import HelloWorld._
    val (success, out, err) = CliSuite.withReporter { reporter =>
      val format = scala.meta.metap.Format.Detailed
      val settings =
        scala.meta.metap.Settings().withFormat(format).withPaths(List(semanticdb))
      Metap.process(settings, reporter)
    }
    assert(success)
    assertNoDiffOrPrintExpected(
      out,
      """|HelloWorld.scala
       |----------------
       |
       |Summary:
       |Schema => SemanticDB v4
       |Uri => HelloWorld.scala
       |Text => non-empty
       |Language => Scala
       |Symbols => 3 entries
       |Occurrences => 7 entries
       |
       |Symbols:
       |_empty_/HelloWorld. => final object HelloWorld extends AnyRef { +1 decls }
       |  AnyRef => scala/AnyRef#
       |_empty_/HelloWorld.main(). => method main(args: Array[String]): Unit
       |  args => _empty_/HelloWorld.main().(args)
       |  Array => scala/Array#
       |  String => scala/Predef.String#
       |  Unit => scala/Unit#
       |_empty_/HelloWorld.main().(args) => param args: Array[String]
       |  Array => scala/Array#
       |  String => scala/Predef.String#
       |
       |Occurrences:
       |[0:7..0:17): HelloWorld <= _empty_/HelloWorld.
       |[1:6..1:10): main <= _empty_/HelloWorld.main().
       |[1:11..1:15): args <= _empty_/HelloWorld.main().(args)
       |[1:17..1:22): Array => scala/Array#
       |[1:23..1:29): String => scala/Predef.String#
       |[1:33..1:37): Unit => scala/Unit#
       |[2:4..2:11): println => scala/Predef.println(+1).
    """.trim.stripMargin
    )
    assert(err.isEmpty)
  }

  test("metac only outputs semanticdb for files in sourceroot") {
    val projectroot = StringFS
      .fromString(
        """|/sourceroot_/Left.scala
           |object Left { def right = 42 }
           |/not_sourceroot_/Right.scala
           |object Right { def left = 41 }
           |""".stripMargin
      )
      .toNIO
    val sourceroot = projectroot.resolve("sourceroot_")
    val notSourceroot = projectroot.resolve("not_sourceroot_")
    val inSourcerootScala = sourceroot.resolve("Left.scala")
    val notInSourcerootScala = notSourceroot.resolve("Right.scala")

    val target = Files.createTempDirectory("target_")
    val inSourcerootSemanticdb = target.resolve("META-INF/semanticdb/Left.scala.semanticdb")

    val (success, out, err) = CliSuite.communicate { (out, err) =>
      val scalacArgs = List(
        "-cp",
        Library.scalaLibrary.classpath().syntax,
        "-P:semanticdb:sourceroot:" + sourceroot.toString,
        "-P:semanticdb:text:on",
        "-d",
        target.toString,
        inSourcerootScala.toString,
        notInSourcerootScala.toString
      )
      val settings = scala.meta.metac.Settings().withScalacArgs(scalacArgs)
      val reporter = Reporter()
      Metac.process(settings, reporter)
    }

    assert(success)
    assert(out.isEmpty)
    assert(Files.exists(inSourcerootSemanticdb))

    def searchForRightScalaSemanticdbIn(in: Path): Iterator[Path] =
      Files
        .walk(in)
        .iterator()
        .asScala
        .filter(_.toString.contains("Right.scala.semanticdb"))

    val rightResults = searchForRightScalaSemanticdbIn(projectroot) ++ searchForRightScalaSemanticdbIn(
      target
    )
    assert(rightResults.isEmpty)
  }
}

object CliSuite {
  def withReporter[T](op: Reporter => T): (T, String, String) = {
    communicate { (out, err) =>
      op(Reporter().withOut(out).withErr(err))
    }
  }
  def communicate[T](op: (PrintStream, PrintStream) => T): (T, String, String) = {
    val outbaos = new ByteArrayOutputStream
    val outps = new PrintStream(outbaos, true, UTF_8.name)
    val errbaos = new ByteArrayOutputStream
    val errps = new PrintStream(errbaos, true, UTF_8.name)
    val result = op(outps, errps)
    val outs = new String(outbaos.toByteArray, UTF_8)
    val errs = new String(errbaos.toByteArray, UTF_8)
    (result, outs, errs)
  }
}