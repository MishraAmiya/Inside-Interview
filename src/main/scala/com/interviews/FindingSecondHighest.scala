package com.interviews

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, RelationalGroupedDataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

/**
  * Created by AMIYA on 4/14/2019.
  */
object FindingSecondHighest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionZipsExample")
      .master("local")
      .getOrCreate()
    val originalDf:DataFrame = spark.read.option("header",true).csv("D:\\Scala_Exercise\\src\\main\\input\\secondHighest")

    val w = Window.partitionBy("department_name").orderBy(desc("emp_salary"))

    val finaloutput: DataFrame = originalDf.withColumn("rank",dense_rank().over(w)).where("rank = 2").drop("rank")

    finaloutput.show()

  }


}
