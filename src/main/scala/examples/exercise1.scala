package examples

import scala.io._

object exercise1 extends App {

  val data = Source.fromFile("data/DataSet1.csv").getLines().toList

  val header = data(0)

  case class Schema (Name:String, Age: Int)

  val splittedData = data
    .filter(x=>x!=header)
    .map(x=>Schema(x.split(",")(0),x.split(",")(1).toInt)).toList

  splittedData.distinct.foreach(println)

}
