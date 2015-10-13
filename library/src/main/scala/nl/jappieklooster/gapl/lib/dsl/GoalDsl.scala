package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.model.Agent
import nl.jappieklooster.groovy.meta.IMissingMethodHandler
import org.slf4j.{Logger, LoggerFactory}

class GoalDsl(var subject:Agent) extends IMissingMethodHandler{
	private val log: Logger = LoggerFactory.getLogger(classOf[GoalDsl])

	def methodMissing(name: String, args: Array[AnyRef]): Unit= {
		if(subject.goals.contains(name)){
			log.info(s"$name is overwritten")
		}
		if(args.length > 1){
			log.warn("to many arguments for goal")
			return
		}
		args.apply(0) match{
			case commands:Closure[Void] =>
				subject = subject.copy(goals = subject.goals + (name -> commands))
		}
	}
}