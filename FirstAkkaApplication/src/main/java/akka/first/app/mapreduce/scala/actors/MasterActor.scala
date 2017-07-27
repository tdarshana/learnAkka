package akka.first.app.mapreduce.scala.actors

import akka.actor.actorRef2Scala
import akka.actor.Actor
import akka.actor.Props
import akka.first.app.mapreduce.scala.{MapData, ReduceData, Result}
import akka.routing.RoundRobinRouter

class MasterActor extends Actor {

	val mapActor = context.actorOf(Props[MapActor].withRouter( RoundRobinRouter(nrOfInstances = 5)), name = "map")
	val reduceActor = context.actorOf(Props[ReduceActor].withRouter( RoundRobinRouter(nrOfInstances = 5)), name = "reduce")
	val aggregateActor = context.actorOf(Props[AggregateActor], name = "aggregate")

	def receive: Receive = {
		case line: String =>
			mapActor ! line
		case mapData: MapData =>
			reduceActor ! mapData
		case reduceData: ReduceData =>
			aggregateActor ! reduceData
		case Result =>
			aggregateActor forward Result
	}
}
