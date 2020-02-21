package com.fpt.parser.logtype.click
class InfoRequestUriColumn {
  case class FieldInRequestUriColumn(keyName: String, regex: String, typeValues: String)

  val  fieldP = FieldInRequestUriColumn("p", """[\?+&+]p=[^&]*""", "string")
  val  fieldAid = FieldInRequestUriColumn("aid", """[\?+&+]aid=[^&]*""", "string")
  val  fieldAr  = FieldInRequestUriColumn("ar", """[\?+&+]ar=[^&]*""", "int")
  val  fieldAst  = FieldInRequestUriColumn("ast", """[\?+&+]ast=[^&]*""", "long")
  val  fieldAv  = FieldInRequestUriColumn("av", """[\?+&+]av=[^&]*""", "string")
  val  fieldDid  = FieldInRequestUriColumn("did", """[\?+&+]did=[^&]*""", "string")
  val  fieldDmf  = FieldInRequestUriColumn("dmf", """[\?+&+]dmf=[^&]*""", "string")
  val  fieldDml  = FieldInRequestUriColumn("dml", """[\?+&+]dml=[^&]*""", "string")
  val  fieldDtm  = FieldInRequestUriColumn("dtm", """[\?+&+]dtm=[^&]*""", "string")
  val  fieldEac  = FieldInRequestUriColumn("eac", """[\?+&+]eac=[^&]*""", "string")
  val  fieldEca  = FieldInRequestUriColumn("eac", """[\?+&+]eca=[^&]*""", "string")
  val  fieldEid  = FieldInRequestUriColumn("eid", """[\?+&+]eid=[^&]*""", "string")
  val  fieldEla  = FieldInRequestUriColumn("ela", """[\?+&+]ela=[^&]*""", "string")
  val  fieldEpn  = FieldInRequestUriColumn("epn", """[\?+&+]epn=[^&]*""", "string")
  val  fieldEpr  = FieldInRequestUriColumn("epr", """[\?+&+]epr=[^&]*""", "string")
  val  fieldEv  = FieldInRequestUriColumn("ev", """[\?+&+]ev=[^&]*""", "int")
  val  fieldOv  = FieldInRequestUriColumn("ov", """[\?+&+]ov=[^&]*""", "int")
  val  fieldPl_st  = FieldInRequestUriColumn("pl_st", """[\?+&+]pl_st=[^&]*""", "long")
  val  fieldPlf  = FieldInRequestUriColumn("plf", """[\?+&+]plf=[^&]*""", "int")
  val  fieldPn  = FieldInRequestUriColumn("pn", """[\?+&+]pn=[^&]*""", "string")
  val  fieldRes  = FieldInRequestUriColumn("res", """[\?+&+]res=[^&]*""", "string")
  val  fieldScc  = FieldInRequestUriColumn("scc", """[\?+&+]scc=[^&]*""", "string")
  val  fieldScid  = FieldInRequestUriColumn("scid", """[\?+&+]scid=[^&]*""", "string")
  val  fieldScn  = FieldInRequestUriColumn("scn", """[\?+&+]scn=[^&]*""", "string")
  val  fieldScp  = FieldInRequestUriColumn("scp", """[\?+&+]scp=[^&]*""", "string")
  val  fieldScv  = FieldInRequestUriColumn("scv", """[\?+&+]scv=[^&]*""", "string")
  val  fieldTna  = FieldInRequestUriColumn("tna", """[\?+&+]tna=[^&]*""", "string")
  val  fieldTpe  = FieldInRequestUriColumn("tpe", """[\?+&+]tpe=[^&]*""", "string")
  val  fieldTz  = FieldInRequestUriColumn("tz", """[\?+&+]tz=[^&]*""", "string")
  val  fieldUid  = FieldInRequestUriColumn("uid", """[\?+&+]uid=[^&]*""", "int")
  val  fieldVdl  = FieldInRequestUriColumn("vdl", """[\?+&+]vdl=[^&]*""", "float")
  val  fieldMac  = FieldInRequestUriColumn("mac", """[\?+&+]mac=[^&]*""", "string")

  val fieldList = List(fieldP, fieldAid, fieldAr, fieldAst, fieldAv, fieldDid,
    fieldDmf, fieldDml, fieldDtm, fieldEac, fieldEca, fieldEid, fieldEla,
    fieldEpn, fieldEpr, fieldEv, fieldOv, fieldPl_st, fieldPlf, fieldPn,
    fieldRes, fieldScc, fieldScid, fieldScn, fieldScp, fieldScv, fieldTna,
    fieldTpe, fieldTz, fieldUid, fieldVdl, fieldMac
  )

}
