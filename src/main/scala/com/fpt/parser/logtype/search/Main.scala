package com.fpt.parser.logtype.search

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
      PathFileLogsJson = config.getString("search.pathInputFile")
      PathFileDestination = config.getString("search.pathOutputFile")
    }
    val spark = SparkSession.builder()
      .appName("parse logs search")
      .master("local")
      .getOrCreate()

    val parser = new ParserLogsSearch()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.parseTimestamp(logsDf)

    val infoAllColumnSearch = new InfoAllColumnSearch()
    logsDf = parser.reDefiniteTypeColumn(logsDf, infoAllColumnSearch)

    val infoRequestUriSearch = new InfoRequestUriColumnSearch()
    logsDf = parser.parseRequestUriColumn(logsDf, infoRequestUriSearch)

    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
