package com.spark.dataLoadWrite

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object s5_dataLoadingMySql {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrame").
      setMaster("local[4]")
    var sc = new SparkContext(conf)
    val spark = new SQLContext(sc)
    import spark.implicits._

    ///////////////////////////  데이터 파일 로딩  ////////////////////////////////////
    // 접속정보 설정 (1)
    var staticUrl = "jdbc:mysql://ipaddress:3306/kopo"

    var staticUser = "id"
    var staticPw = "password"
    var selloutDb = "kopo_product_volume"

    // 관계형 데이터베이스 Oracle 연결 (2)
    val dataFromMySql= spark.read.format("jdbc").
      option("url",staticUrl).
      option("dbtable",selloutDb).
      option("user",staticUser).
      option("password",staticPw).load

    // 데이터 확인 (3)
    println(dataFromMySql.show(5))
  }
}
