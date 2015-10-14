package nl.jappieklooster.gapl.lib.controller

import akka.actor.Actor
import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.dsl.Delegator
import nl.jappieklooster.gapl.lib.dsl.execution.BelieveExecutionDsl
import nl.jappieklooster.gapl.lib.model.Agent
import org.slf4j.LoggerFactory

/**
 * Execute a single agent.
 */
class AgentController(var agent:Agent) extends Actor with Delegator{
	val log = LoggerFactory.getLogger(classOf[AgentController])
	def execute(): Unit = {
		var resultingGoals = agent.goals
		for((name, value) <- agent.goals){
			if(goalComplete(name)){
				resultingGoals -= name
			}
		}
		agent = agent.copy(goals = resultingGoals)
	}
	def goalComplete(name:String):Boolean = {
		for(believeValueArray <- agent.believes get name){
			if(believeValueArray.size > 1){
				return false
			}
			for(believe <- believeValueArray.find((a:Any) => true)){
				believe match{
					case deduction:Closure[Boolean] =>
						log.debug("checking")
						log.info("the if the believe finished")
						return delegate(deduction, new BelieveExecutionDsl(agent))
				}
			}
		}
		return false
	}

	override def receive: Receive = ???
}
