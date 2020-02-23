package com.fpt.parser.logtype.view

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
      PathFileLogsJson = config.getString("view.pathInputFile")
      PathFileDestination = config.getString("view.pathOutputFile")
    }
    val spark = SparkSession.builder()
      .appName("parse logs view")
      .master("local")
      .getOrCreate()

    val parser = new ParserLogsView()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.parseTimestamp(logsDf)

    val infoAllColumnView = new InfoAllColumnView()
    logsDf = parser.reDefiniteTypeColumn(logsDf, infoAllColumnView)

    val infoRequestUriView = new InfoRequestUriColumnView()
    logsDf = parser.parseRequestUriColumn(logsDf, infoRequestUriView)

    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
