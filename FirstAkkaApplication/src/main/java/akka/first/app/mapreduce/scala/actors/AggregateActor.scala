package akka.first.app.mapreduce.scala.actors

import scala.collection.immutable.Map
import scala.collection.mutable.HashMap

import akka.actor.Actor
import akka.first.app.mapreduce.scala.{ReduceData, Result}

class AggregateActor extends Actor {
  val finalReducedMap = new HashMap[String, Int]

  def receive: Receive = {
    case ReduceData(reduceDataMap) =>
      aggregateInMemoryReduce(reduceDataMap)
    case Result =>
      sender ! finalReducedMap.toString()
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Int]): Unit = {
    for ((key, value) <- reducedList) {
      if (finalReducedMap contains key)
        finalReducedMap(key) = (value + finalReducedMap.get(key).get)
      else
        finalReducedMap += (key -> value)
    }
  }
}
