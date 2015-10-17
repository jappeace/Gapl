package nl.jappieklooster.gapl.lib.controller


import java.nio.file.Path

import akka.actor._
import nl.jappieklooster.gapl.lib.loader.AgentLoader
import nl.jappieklooster.gapl.lib.model.{Agent, Command}
import org.slf4j.LoggerFactory


/**
 * Makes the other agent controllers tick by sending them messages
 */
class MultiAgentController(path:Path) {
	private var actors:Seq[ActorRef] = Nil
	var log = LoggerFactory.getLogger(classOf[MultiAgentController])
	def start(environment: AnyRef) = {
		if(actors != Nil){
			log.warn("actors still exist, " +
					 "system should be stopped before restarting")
		}
		val system = ActorSystem(this.getClass.getSimpleName)
		actors = createAgents().map(
			a => {
				val r = system.actorOf(Props(classOf[AgentController], a, environment))
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
	private def createAgents():Seq[Agent] = {
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
