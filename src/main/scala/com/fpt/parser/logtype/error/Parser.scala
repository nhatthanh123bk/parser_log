package com.fpt.parser.logtype.error

import com.fpt.parser.common.StepsParseLog
import org.apache.spark.sql
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

class Parser() extends StepsParseLog {
  override def readFileLog(spark: SparkSession, path: String): sql.DataFrame = {
    var logsDf = spark.read.json(path)
    return logsDf
  }
  override def reDefiniteTypeColumn(df: sql.DataFrame): sql.DataFrame = {
    var logsDf = df
    val infoAllColumn = new InfoAllColumn()
    for(infoColumn <- infoAllColumn.listColumn) {
      infoColumn.typeValues match {
        case "string" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(StringType))
        }
        case "int" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(IntegerType))
        }
        case "float" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(FloatType))
        }
        case "long" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(LongType))
        }
        case "boolean" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(BooleanType))
        }
        case "double" => {
          logsDf = logsDf.withColumn(infoColumn.nameColumn, col(infoColumn.nameColumn).cast(DoubleType))
        }
      }
    }
    return logsDf
  }

  override def parseTimestamp(df: DataFrame): DataFrame = {
    var logsDf = df
    val regexDate = """\d{4}-\d{2}-\d{2}"""
    val regexTime = """\d{2}:\d{2}:\d{2}.\d{3}"""
    logsDf = logsDf.withColumn("date", to_date(regexp_extract(col("@timestamp"), regexDate, 0)))
      .withColumn("time", regexp_extract(col("@timestamp"), regexTime, 0))
      .withColumn("@timestamp", regexp_replace(col("@timestamp"), "T", " "))
      .withColumn("@timestamp", regexp_replace(col("@timestamp"), "Z", ""))
    return logsDf
  }

  override def parseRequestUriColumn(df: sql.DataFrame): sql.DataFrame = {
    var logsDf = df
    val infoRequestUri = new InfoRequestUriColumn()
    for(field <- infoRequestUri.fieldList) {
      field.typeValues match {
        case "string" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(StringType))
        }
        case "int" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(IntegerType))
        }
        case "float" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(FloatType))
        }
        case "long" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(LongType))
        }
        case "double" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(DoubleType))
        }
        case "boolean" => {
          logsDf = logsDf.withColumn(field.keyName, regexp_extract(col("request_uri"), field.regex, 0))
            .withColumn(field.keyName, regexp_replace(col(field.keyName), """[\?+&+]\w+=""", ""))
            .withColumn(field.keyName, col(field.keyName).cast(BooleanType))
        }
      }
    }
    return logsDf
  }

  override def saveToParquet(logsDf: sql.DataFrame, path: String): Unit = {
    logsDf.write.mode("overwrite")
      .parquet(path)
  }
}
