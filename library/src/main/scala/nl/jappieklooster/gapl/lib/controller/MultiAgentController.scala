package nl.jappieklooster.gapl.lib.controller


import java.nio.file.Path

import akka.actor._
import nl.jappieklooster.gapl.lib.loader.AgentLoader
import nl.jappieklooster.gapl.lib.model.{Command, Update, Agent}
import nl.jappieklooster.gapl.lib.model.message.Update

import scala.collection.TraversableLike


/**
 * Makes the other agent controllers tick by sending them messages
 */
class MultiAgentController(path:Path) {
	private var actors:Seq[ActorRef] = Nil
	def start(environment: AnyRef) = {
		val system = ActorSystem("s")
		actors = createAgents().map(
			a => {
				val r = system.actorOf(Props(classOf[AgentController], a))
				r ! Command.Start
				r
			}
		)
	}
	def stop(): Unit ={
		for(actor <- actors){
			actor ! Command.Stop
		}
	}
	def createAgents():Seq[Agent] = {
		var result:Seq[Agent] = Vector()
		val loader = new AgentLoader
		val file = path.toFile
		if(file.isFile){
			result =  result :+ loader.load(file)
		}
		if(file.isDirectory){
			for(f <- file.listFiles()){
				result =  result :+ loader.load(f)
			}
		}
		return result
	}
}
