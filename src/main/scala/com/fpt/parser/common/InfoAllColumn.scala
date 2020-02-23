package com.fpt.parser.common

class InfoAllColumn {
  case class InfoColumn(nameColumn: String, typeValues: String)
  val listColumn: List[InfoColumn] = List(InfoColumn("",""))
}
