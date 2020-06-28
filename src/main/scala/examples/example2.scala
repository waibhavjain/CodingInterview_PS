package examples

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.SparkContext

object example2 extends App{

  val sc = new SparkContext("local", "name")

  sc.setLogLevel("ERROR")

  val hc = new HiveContext(sc)

  hc.sql(
    """
      |CREATE TABLE IF NOT EXISTS clickstream_data (
      |timestamp string,
      |userid string)
      |ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
      |""".stripMargin)

  hc.sql(
    """
      |LOAD DATA LOCAL INPATH 'data/clickStream.csv'
      |OVERWRITE INTO TABLE clickstream_data
      |""".stripMargin)

  hc.sql("select to_date(timestamp) as Day,count(1) as SessionCount from clickstream_data group by to_date(timestamp)").show()

