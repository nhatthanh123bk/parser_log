package com.fpt.parser.common

import org.apache.spark.sql
import org.apache.spark.sql.SparkSession

trait StepsParseLog {
  def readFileLog(spark: SparkSession, path: String): sql.DataFrame
  def reDefiniteTypeColumn(df: sql.DataFrame): sql.DataFrame
  def parseRequestUriColumn(df: sql.DataFrame): sql.DataFrame
  def parseTimestamp(df: sql.DataFrame): sql.DataFrame
  def saveToParquet(df: sql.DataFrame, path: String): Unit
}
