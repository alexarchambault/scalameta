// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package scala.meta.internal.semanticdb3

@SerialVersionUID(0L)
final case class TextDocument(
    schema: scala.meta.internal.semanticdb3.Schema = scala.meta.internal.semanticdb3.Schema.LEGACY,
    uri: _root_.scala.Predef.String = "",
    text: _root_.scala.Predef.String = "",
    language: scala.meta.internal.semanticdb3.Language = scala.meta.internal.semanticdb3.Language.UNKNOWN_LANGUAGE,
    symbols: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolInformation] = _root_.scala.collection.Seq.empty,
    occurrences: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolOccurrence] = _root_.scala.collection.Seq.empty,
    diagnostics: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Diagnostic] = _root_.scala.collection.Seq.empty,
    synthetics: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Synthetic] = _root_.scala.collection.Seq.empty
    ) extends scalapb.GeneratedMessage with scalapb.Message[TextDocument] with scalapb.lenses.Updatable[TextDocument] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = schema
        if (__value != scala.meta.internal.semanticdb3.Schema.LEGACY) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeEnumSize(1, __value.value)
        }
      };
      
      {
        val __value = uri
        if (__value != "") {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
        }
      };
      
      {
        val __value = text
        if (__value != "") {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(3, __value)
        }
      };
      
      {
        val __value = language
        if (__value != scala.meta.internal.semanticdb3.Language.UNKNOWN_LANGUAGE) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeEnumSize(10, __value.value)
        }
      };
      symbols.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      occurrences.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      diagnostics.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      synthetics.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      __size
    }
    final override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = schema
        if (__v != scala.meta.internal.semanticdb3.Schema.LEGACY) {
          _output__.writeEnum(1, __v.value)
        }
      };
      {
        val __v = uri
        if (__v != "") {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = text
        if (__v != "") {
          _output__.writeString(3, __v)
        }
      };
      symbols.foreach { __v =>
        val __m = __v
        _output__.writeTag(5, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      occurrences.foreach { __v =>
        val __m = __v
        _output__.writeTag(6, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      diagnostics.foreach { __v =>
        val __m = __v
        _output__.writeTag(7, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      synthetics.foreach { __v =>
        val __m = __v
        _output__.writeTag(8, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = language
        if (__v != scala.meta.internal.semanticdb3.Language.UNKNOWN_LANGUAGE) {
          _output__.writeEnum(10, __v.value)
        }
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): scala.meta.internal.semanticdb3.TextDocument = {
      var __schema = this.schema
      var __uri = this.uri
      var __text = this.text
      var __language = this.language
      val __symbols = (_root_.scala.collection.immutable.Vector.newBuilder[scala.meta.internal.semanticdb3.SymbolInformation] ++= this.symbols)
      val __occurrences = (_root_.scala.collection.immutable.Vector.newBuilder[scala.meta.internal.semanticdb3.SymbolOccurrence] ++= this.occurrences)
      val __diagnostics = (_root_.scala.collection.immutable.Vector.newBuilder[scala.meta.internal.semanticdb3.Diagnostic] ++= this.diagnostics)
      val __synthetics = (_root_.scala.collection.immutable.Vector.newBuilder[scala.meta.internal.semanticdb3.Synthetic] ++= this.synthetics)
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 8 =>
            __schema = scala.meta.internal.semanticdb3.Schema.fromValue(_input__.readEnum())
          case 18 =>
            __uri = _input__.readString()
          case 26 =>
            __text = _input__.readString()
          case 80 =>
            __language = scala.meta.internal.semanticdb3.Language.fromValue(_input__.readEnum())
          case 42 =>
            __symbols += _root_.scalapb.LiteParser.readMessage(_input__, scala.meta.internal.semanticdb3.SymbolInformation.defaultInstance)
          case 50 =>
            __occurrences += _root_.scalapb.LiteParser.readMessage(_input__, scala.meta.internal.semanticdb3.SymbolOccurrence.defaultInstance)
          case 58 =>
            __diagnostics += _root_.scalapb.LiteParser.readMessage(_input__, scala.meta.internal.semanticdb3.Diagnostic.defaultInstance)
          case 66 =>
            __synthetics += _root_.scalapb.LiteParser.readMessage(_input__, scala.meta.internal.semanticdb3.Synthetic.defaultInstance)
          case tag => _input__.skipField(tag)
        }
      }
      scala.meta.internal.semanticdb3.TextDocument(
          schema = __schema,
          uri = __uri,
          text = __text,
          language = __language,
          symbols = __symbols.result(),
          occurrences = __occurrences.result(),
          diagnostics = __diagnostics.result(),
          synthetics = __synthetics.result()
      )
    }
    def withSchema(__v: scala.meta.internal.semanticdb3.Schema): TextDocument = copy(schema = __v)
    def withUri(__v: _root_.scala.Predef.String): TextDocument = copy(uri = __v)
    def withText(__v: _root_.scala.Predef.String): TextDocument = copy(text = __v)
    def withLanguage(__v: scala.meta.internal.semanticdb3.Language): TextDocument = copy(language = __v)
    def clearSymbols = copy(symbols = _root_.scala.collection.Seq.empty)
    def addSymbols(__vs: scala.meta.internal.semanticdb3.SymbolInformation*): TextDocument = addAllSymbols(__vs)
    def addAllSymbols(__vs: TraversableOnce[scala.meta.internal.semanticdb3.SymbolInformation]): TextDocument = copy(symbols = symbols ++ __vs)
    def withSymbols(__v: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolInformation]): TextDocument = copy(symbols = __v)
    def clearOccurrences = copy(occurrences = _root_.scala.collection.Seq.empty)
    def addOccurrences(__vs: scala.meta.internal.semanticdb3.SymbolOccurrence*): TextDocument = addAllOccurrences(__vs)
    def addAllOccurrences(__vs: TraversableOnce[scala.meta.internal.semanticdb3.SymbolOccurrence]): TextDocument = copy(occurrences = occurrences ++ __vs)
    def withOccurrences(__v: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolOccurrence]): TextDocument = copy(occurrences = __v)
    def clearDiagnostics = copy(diagnostics = _root_.scala.collection.Seq.empty)
    def addDiagnostics(__vs: scala.meta.internal.semanticdb3.Diagnostic*): TextDocument = addAllDiagnostics(__vs)
    def addAllDiagnostics(__vs: TraversableOnce[scala.meta.internal.semanticdb3.Diagnostic]): TextDocument = copy(diagnostics = diagnostics ++ __vs)
    def withDiagnostics(__v: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Diagnostic]): TextDocument = copy(diagnostics = __v)
    def clearSynthetics = copy(synthetics = _root_.scala.collection.Seq.empty)
    def addSynthetics(__vs: scala.meta.internal.semanticdb3.Synthetic*): TextDocument = addAllSynthetics(__vs)
    def addAllSynthetics(__vs: TraversableOnce[scala.meta.internal.semanticdb3.Synthetic]): TextDocument = copy(synthetics = synthetics ++ __vs)
    def withSynthetics(__v: _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Synthetic]): TextDocument = copy(synthetics = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = schema.javaValueDescriptor
          if (__t.getNumber() != 0) __t else null
        }
        case 2 => {
          val __t = uri
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = text
          if (__t != "") __t else null
        }
        case 10 => {
          val __t = language.javaValueDescriptor
          if (__t.getNumber() != 0) __t else null
        }
        case 5 => symbols
        case 6 => occurrences
        case 7 => diagnostics
        case 8 => synthetics
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PEnum(schema.scalaValueDescriptor)
        case 2 => _root_.scalapb.descriptors.PString(uri)
        case 3 => _root_.scalapb.descriptors.PString(text)
        case 10 => _root_.scalapb.descriptors.PEnum(language.scalaValueDescriptor)
        case 5 => _root_.scalapb.descriptors.PRepeated(symbols.map(_.toPMessage)(_root_.scala.collection.breakOut))
        case 6 => _root_.scalapb.descriptors.PRepeated(occurrences.map(_.toPMessage)(_root_.scala.collection.breakOut))
        case 7 => _root_.scalapb.descriptors.PRepeated(diagnostics.map(_.toPMessage)(_root_.scala.collection.breakOut))
        case 8 => _root_.scalapb.descriptors.PRepeated(synthetics.map(_.toPMessage)(_root_.scala.collection.breakOut))
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = scala.meta.internal.semanticdb3.TextDocument
}

object TextDocument extends scalapb.GeneratedMessageCompanion[scala.meta.internal.semanticdb3.TextDocument] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[scala.meta.internal.semanticdb3.TextDocument] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): scala.meta.internal.semanticdb3.TextDocument = {
    require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    scala.meta.internal.semanticdb3.TextDocument(
      scala.meta.internal.semanticdb3.Schema.fromValue(__fieldsMap.getOrElse(__fields.get(0), scala.meta.internal.semanticdb3.Schema.LEGACY.javaValueDescriptor).asInstanceOf[_root_.com.google.protobuf.Descriptors.EnumValueDescriptor].getNumber),
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[_root_.scala.Predef.String],
      __fieldsMap.getOrElse(__fields.get(2), "").asInstanceOf[_root_.scala.Predef.String],
      scala.meta.internal.semanticdb3.Language.fromValue(__fieldsMap.getOrElse(__fields.get(3), scala.meta.internal.semanticdb3.Language.UNKNOWN_LANGUAGE.javaValueDescriptor).asInstanceOf[_root_.com.google.protobuf.Descriptors.EnumValueDescriptor].getNumber),
      __fieldsMap.getOrElse(__fields.get(4), Nil).asInstanceOf[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolInformation]],
      __fieldsMap.getOrElse(__fields.get(5), Nil).asInstanceOf[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolOccurrence]],
      __fieldsMap.getOrElse(__fields.get(6), Nil).asInstanceOf[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Diagnostic]],
      __fieldsMap.getOrElse(__fields.get(7), Nil).asInstanceOf[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Synthetic]]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[scala.meta.internal.semanticdb3.TextDocument] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      scala.meta.internal.semanticdb3.TextDocument(
        scala.meta.internal.semanticdb3.Schema.fromValue(__fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scalapb.descriptors.EnumValueDescriptor]).getOrElse(scala.meta.internal.semanticdb3.Schema.LEGACY.scalaValueDescriptor).number),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        scala.meta.internal.semanticdb3.Language.fromValue(__fieldsMap.get(scalaDescriptor.findFieldByNumber(10).get).map(_.as[_root_.scalapb.descriptors.EnumValueDescriptor]).getOrElse(scala.meta.internal.semanticdb3.Language.UNKNOWN_LANGUAGE.scalaValueDescriptor).number),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(5).get).map(_.as[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolInformation]]).getOrElse(_root_.scala.collection.Seq.empty),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(6).get).map(_.as[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolOccurrence]]).getOrElse(_root_.scala.collection.Seq.empty),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(7).get).map(_.as[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Diagnostic]]).getOrElse(_root_.scala.collection.Seq.empty),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(8).get).map(_.as[_root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Synthetic]]).getOrElse(_root_.scala.collection.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = Semanticdb3Proto.javaDescriptor.getMessageTypes.get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = Semanticdb3Proto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 5 => __out = scala.meta.internal.semanticdb3.SymbolInformation
      case 6 => __out = scala.meta.internal.semanticdb3.SymbolOccurrence
      case 7 => __out = scala.meta.internal.semanticdb3.Diagnostic
      case 8 => __out = scala.meta.internal.semanticdb3.Synthetic
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = {
    (__fieldNumber: @_root_.scala.unchecked) match {
      case 1 => scala.meta.internal.semanticdb3.Schema
      case 10 => scala.meta.internal.semanticdb3.Language
    }
  }
  lazy val defaultInstance = scala.meta.internal.semanticdb3.TextDocument(
  )
  implicit class TextDocumentLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, scala.meta.internal.semanticdb3.TextDocument]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, scala.meta.internal.semanticdb3.TextDocument](_l) {
    def schema: _root_.scalapb.lenses.Lens[UpperPB, scala.meta.internal.semanticdb3.Schema] = field(_.schema)((c_, f_) => c_.copy(schema = f_))
    def uri: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.uri)((c_, f_) => c_.copy(uri = f_))
    def text: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.text)((c_, f_) => c_.copy(text = f_))
    def language: _root_.scalapb.lenses.Lens[UpperPB, scala.meta.internal.semanticdb3.Language] = field(_.language)((c_, f_) => c_.copy(language = f_))
    def symbols: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolInformation]] = field(_.symbols)((c_, f_) => c_.copy(symbols = f_))
    def occurrences: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.SymbolOccurrence]] = field(_.occurrences)((c_, f_) => c_.copy(occurrences = f_))
    def diagnostics: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Diagnostic]] = field(_.diagnostics)((c_, f_) => c_.copy(diagnostics = f_))
    def synthetics: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.Seq[scala.meta.internal.semanticdb3.Synthetic]] = field(_.synthetics)((c_, f_) => c_.copy(synthetics = f_))
  }
  final val SCHEMA_FIELD_NUMBER = 1
  final val URI_FIELD_NUMBER = 2
  final val TEXT_FIELD_NUMBER = 3
  final val LANGUAGE_FIELD_NUMBER = 10
  final val SYMBOLS_FIELD_NUMBER = 5
  final val OCCURRENCES_FIELD_NUMBER = 6
  final val DIAGNOSTICS_FIELD_NUMBER = 7
  final val SYNTHETICS_FIELD_NUMBER = 8
}
