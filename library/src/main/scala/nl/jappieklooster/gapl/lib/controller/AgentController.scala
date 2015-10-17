package nl.jappieklooster.gapl.lib.controller

import akka.actor.Actor
import groovy.lang.Closure
import nl.jappieklooster.gapl.Log
import nl.jappieklooster.gapl.lib.dsl.Delegator
import nl.jappieklooster.gapl.lib.dsl.execution.{BelieveExecutionDsl, GoalExecutionDsl}
import nl.jappieklooster.gapl.lib.model.{Agent, Command, Update}

/**
 * Execute a single agent.
 */
class AgentController(private var agent:Agent, environment:AnyRef) extends Actor with Delegator{
	val log = Log.get[AgentController]
	def execute(): Unit = {
		var resultingGoals = agent.goals
		for((name, value) <- agent.goals){
			if(goalComplete(name)){
				resultingGoals -= name
			}else{
				delegate(value, new GoalExecutionDsl(agent, environment))
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
						return delegate(deduction, new BelieveExecutionDsl(agent))
				}
			}
		}
		return false
	}

	private var isStopped = false
	override def receive: Receive = {
		case Command.Stop =>
			isStopped = true
		case Command.Start =>
			isStopped = false
			self ! Update(1)
		case Update(tickNumber) => {
			execute()
			if(!isStopped){
				self ! Update(tickNumber+1)
			}
		}
	}
}
