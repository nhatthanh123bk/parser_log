package com.fpt.parser.logtype.error

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    var PathFileLogsJson: String = ""
    var PathFileDestination: String = ""
    if(args.length > 2) {
      PathFileLogsJson = args(0)
      PathFileDestination = args(1)
    }
    else {
      val config: Config = ConfigFactory.load()
      PathFileLogsJson = config.getString("error.pathInputFile")
      PathFileDestination = config.getString("error.pathOutputFile")
    }
    val spark = SparkSession.builder()
      .appName("parse logs error")
      .master("local")
      .getOrCreate()

    val parser = new ParserLogsError()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.parseTimestamp(logsDf)

    val infoAllColumnError = new InfoAllColumnError()
    logsDf = parser.reDefiniteTypeColumn(logsDf, infoAllColumnError)

    val infoRequestUriError = new InfoRequestUriColumnError()
    logsDf = parser.parseRequestUriColumn(logsDf, infoRequestUriError)

    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
