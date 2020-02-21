package com.fpt.parser.logtype.click

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
      PathFileLogsJson = config.getString("click.pathInputFile")
      PathFileDestination = config.getString("click.pathOutputFile")
    }

    val spark = SparkSession.builder()
      .appName("parse logs click")
      .master("local")
      .getOrCreate()

    val parser = new Parser()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.reDefiniteTypeColumn(logsDf)
    logsDf = parser.parseRequestUriColumn(logsDf)
    logsDf = parser.parseTimestamp(logsDf)
    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
