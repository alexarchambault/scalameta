import org.scalatest._
import scala.meta.syntactic.show._

class ShowSuite extends ParseSuite {
  test("val x: Int (raw)") {
    val tree = templStat("val x: Int")
    assert(tree.show[Raw] === "Decl.Val(List(), List(Term.Name(\"x\")), Type.Name(\"Int\"))")
  }

  test("val x: Int (code)") {
    val tree = templStat("val x: Int")
    assert(tree.show[Code] === "val x: Int")
  }

  test("~(1 + 2) + ~x.y(z) + (~x).y(z)") {
    val tree = templStat("~(1 + 2) + ~x.y(z) + (~x).y(z)")
    assert(tree.show[Code] === "~(1 + 2) + ~x.y(z) + (~x).y(z)")
  }

  test("(a + b + c) && (a + (b + c)) && (a :: b :: c) && ((a :: b) :: c)") {
    val tree = templStat("(a + b + c) && (a + (b + c)) && (a :: b :: c) && ((a :: b) :: c)")
    assert(tree.show[Code] === "a + b + c && a + (b + c) && (a :: b :: c) && ((a :: b) :: c)")
  }

  test("(x map y).foo") {
    val tree = templStat("(x map y).foo")
    assert(tree.show[Code] === "(x map y).foo")
  }

  test("string literals with newlines and double quotes") {
    val tree = templStat("""{
      val x = QQQ
        x
      QQQ
      val y = "\""
    }""".replace("QQQ", "\"\"\""))
    assert(tree.show[Raw] === """Term.Block(List(Defn.Val(List(), List(Term.Name("x")), None, Lit.String("\n        x\n      ")), Defn.Val(List(), List(Term.Name("y")), None, Lit.String("\""))))""")
    assert(tree.show[Code] === """
    |{
    |  val x = QQQ
    |        x
    |      QQQ
    |  val y = "\""
    |}
    """.trim.stripMargin.replace("QQQ", "\"\"\""))
  }

  test("interpolations") {
    val tree = templStat("""{
      val x = q"123 + $x + ${foo(123)} + 456"
      val y = QQQ
        $x
        $y
        ..$z
      QQQ
    }""".replace("QQQ", "\"\"\""))
    assert(tree.show[Raw] === """Term.Block(List(Defn.Val(List(), List(Term.Name("x")), None, Term.Interpolate(Term.Name("q"), List(Lit.String("123 + "), Lit.String(" + "), Lit.String(" + 456")), List(Term.Name("x"), Term.Apply(Term.Name("foo"), List(Lit.Int(123)))))), Defn.Val(List(), List(Term.Name("y")), None, Lit.String("\n        $x\n        $y\n        ..$z\n      "))))""")
    assert(tree.show[Code] === """
    |{
    |  val x = q"123 + $x + ${foo(123)} + 456"
    |  val y = QQQ
    |        $x
    |        $y
    |        ..$z
    |      QQQ
    |}
    """.trim.stripMargin.replace("QQQ", "\"\"\""))
  }

  test("foo.bar(bar) { baz }") {
    val tree = templStat("foo.bar(bar) { baz }")
    assert(tree.show[Code] === """
      |foo.bar(bar) {
      |  baz
      |}
    """.trim.stripMargin)
  }

  test("Template.self stringifications") {
    assert(templStat("new { val x = 2 }").show[Code] === "new { val x = 2 }")
    assert(templStat("new { self => val x = 2 }").show[Code] === "new { self => val x = 2 }")
    assert(templStat("new { self: Int => val x = 2 }").show[Code] === "new { self: Int => val x = 2 }")
    assert(templStat("""
      new {
        val x = 2
        val y = 3
      }
    """).show[Code] === """
      |new {
      |  val x = 2
      |  val y = 3
      |}
    """.trim.stripMargin)
    assert(templStat("""
      new { self =>
        val x = 2
        val y = 3
      }
    """).show[Code] === """
      |new { self =>
      |  val x = 2
      |  val y = 3
      |}
    """.trim.stripMargin)
    assert(templStat("""
      new { self: Int =>
        val x = 2
        val y = 3
      }
    """).show[Code] === """
      |new { self: Int =>
      |  val x = 2
      |  val y = 3
      |}
    """.trim.stripMargin)
  }

  test("new X") {
    assert(templStat("new X").show[Code] === "new X")
  }

  test("ascribe and annotate") {
    assert(templStat("_: Int").show[Code] === "_: Int")
    assert(templStat("(_: Int) + 2").show[Code] === "(_: Int) + 2")
    assert(templStat("x: @foo").show[Code] === "x: @foo")
    assert(templStat("(x: @foo) + 2").show[Code] === "(x: @foo) + 2")
  }

  test("compound types") {
    assert(tpe("Foo").show[Code] === "Foo")
    assert(tpe("Foo {}").show[Code] === "Foo")
    assert(tpe("Foo { type T = Int }").show[Code] === "Foo { type T = Int }")
    assert(tpe("Foo { type T = Int; type U <: String }").show[Code] === "Foo { type T = Int; type U <: String }")
    assert(tpe("Foo with Bar").show[Code] === "Foo with Bar")
    assert(tpe("Foo with Bar {}").show[Code] === "Foo with Bar {}")
    assert(tpe("Foo with Bar { type T = Int }").show[Code] === "Foo with Bar { type T = Int }")
    assert(tpe("Foo with Bar { type T = Int; type U <: String }").show[Code] === "Foo with Bar { type T = Int; type U <: String }")
  }

  test("packages") {
    assert(compUnit("package foo.bar; class C").show[Code] === "package foo.bar\nclass C")
    assert(compUnit("package foo.bar; class C; class D").show[Code] === "package foo.bar\nclass C\nclass D")
    assert(compUnit("package foo.bar { class C }").show[Code] === "package foo.bar {\n  class C\n}")
    assert(compUnit("package foo.bar { class C; class D }").show[Code] === "package foo.bar {\n  class C\n  class D\n}")
  }

  test("type parameter mods") {
    assert(compUnit("class C[@foo T]").show[Code] === "class C[@foo T]")
    assert(compUnit("class C[+T]").show[Code] === "class C[+T]")
    assert(compUnit("class C[@foo +T]").show[Code] === "class C[@foo +T]")
  }

  test("primary constructor mods") {
    assert(compUnit("class C").show[Code] === "class C")
    assert(compUnit("class C private").show[Code] === "class C private")
    assert(compUnit("class C @foo(x)").show[Code] === "class C @foo(x)")
    assert(compUnit("class C @foo(x) private").show[Code] === "class C @foo(x) private")
    assert(compUnit("class C(x: Int)").show[Code] === "class C(x: Int)")
    assert(compUnit("class C private (x: Int)").show[Code] === "class C private (x: Int)")
    assert(compUnit("class C @foo(x) (x: Int)").show[Code] === "class C @foo(x) (x: Int)")
    assert(compUnit("class C @foo(x) private (x: Int)").show[Code] === "class C @foo(x) private (x: Int)")
  }
}