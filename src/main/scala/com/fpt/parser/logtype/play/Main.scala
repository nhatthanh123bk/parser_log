package com.fpt.parser.logtype.play

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
      PathFileLogsJson = config.getString("play.pathInputFile")
      PathFileDestination = config.getString("play.pathOutputFile")
    }
    val spark = SparkSession.builder()
      .appName("parse logs Play")
      .master("local")
      .getOrCreate()

    val parser = new ParserLogsPlay()
    var logsDf = parser.readFileLog(spark, PathFileLogsJson)
    logsDf = parser.parseTimestamp(logsDf)

    val infoAllColumnPlay = new InfoAllColumnPlay()
    logsDf = parser.reDefiniteTypeColumn(logsDf, infoAllColumnPlay)

    val infoRequestUriPlay = new InfoRequestUriColumnPlay()
    logsDf = parser.parseRequestUriColumn(logsDf, infoRequestUriPlay)

    parser.saveToParquet(logsDf,PathFileDestination)
  }
}
