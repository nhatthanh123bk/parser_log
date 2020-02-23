package com.fpt.parser.common

class InfoRequestUriColumn {
  case class FieldInRequestUriColumn(keyName: String, regex: String, typeValues: String)
  val fieldList = List(FieldInRequestUriColumn("","","")
  )

}
