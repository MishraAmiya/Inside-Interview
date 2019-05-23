package com.interviews

import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructField

/**
  * Created by AMIYA on 5/24/2019.
  */
object RecordValidation {

//  def getJoinExpression(values: String,df1:DataFrame,df2:DataFrame): Column = {
//    def getvalue(inputValues:Array[String],outvalue: Column, df1:DataFrame,df2:DataFrame): Column = inputValues match{
////      case x => outvalue.
//      case x:Array[String] if(x.length == 1) =>  df1(x(0)) <=> df2(x(0))
//      case y:Array[String] => getvalue(y.tail,df1( y.head) <=> df2(y.head) ,df1,df2)
//    }
//    getvalue(values.split(","),col(),df1,df2)
//  }

  def createJoinKey(lhsKeys: Array[String], rhsKeys: Array[String],df1:DataFrame,df2:DataFrame): Column = (lhsKeys, rhsKeys) match {
    case (l, r) if l.length != r.length => sys.error("key fields should be same")
    case (l, r) if r.tail.length == 0   => return df1(l.head) <=> df2(r.head)
    case (l, r)                         => return (df1(l.head) <=> df2(r.head)).&&(createJoinKey(l.tail, r.tail,df1,df2))
  }

  def getRenamesCols(fields: Array[StructField],renamePrefix:String): Seq[Column] = {
      def getRenamedColumn(fields: Array[StructField],outSeq:Seq[Column]):Seq[Column] = fields match {
        case Array() => outSeq
        case x:Array[StructField] => getRenamedColumn(fields.tail,outSeq ++ Seq(col(x.head.name) as renamePrefix +"_"+x.head.name))
      }
    getRenamedColumn(fields,Seq())
  }

  def constructlhskeys(lhsandrhskeys: Array[String], s: String): Array[String] = {
    lhsandrhskeys.map(field => s +"_"+field)
  }

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().master("local").appName("Record validation").getOrCreate()
    val df1:DataFrame = sparkSession.read.option("header",true).csv("D:\\Scala_Exercise\\src\\main\\input\\input1")
    val df2:DataFrame = sparkSession.read.option("header",true).csv("D:\\Scala_Exercise\\src\\main\\input\\input2")

    val values = "id,name"
    val lhsandrhskeys = values.split(",")

    val lhskeys:Array[String] = constructlhskeys(lhsandrhskeys,"in0")
    val rhskeys :Array[String]= constructlhskeys(lhsandrhskeys,"in1")

    val leftdf = df1.select(getRenamesCols(df1.schema.fields,"in0"):_*)
    val rightdf = df2.select(getRenamesCols(df1.schema.fields,"in1"):_*)

    val expr:Column = createJoinKey(lhskeys,rhskeys,leftdf,rightdf)


    println("==================================")
    println("Expression is : "+ expr)
    println("==================================")

    val fullouterdf = leftdf.join(rightdf,expr ,"full_outer")

    fullouterdf.show()
    fullouterdf.schema.fields.map(field => println(field.name))

  }
}
