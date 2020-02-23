package com.fpt.parser.logtype.ping

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
      PathFileLogsJson = config.getString("ping.pathInputFile")
      PathFileDestination = config.getString("ping.pathOutputFile")
    }
    val spark = SparkSession.builder()
      .appName("parse logs ping")
      .master("local")
      .getOrCreate()

    val parser = new ParserLogsPing()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.parseTimestamp(logsDf)

    val infoAllColumnPing = new InfoAllColumnPing()
    logsDf = parser.reDefiniteTypeColumn(logsDf, infoAllColumnPing)

    val infoRequestUriPing = new InfoRequestUriColumnPing()
    logsDf = parser.parseRequestUriColumn(logsDf, infoRequestUriPing)

    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
