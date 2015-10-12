package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.model.Agent
import nl.jappieklooster.groovy.meta.IMissingMethodHandler
import org.slf4j.{Logger, LoggerFactory}

class BelieveDsl(var subject:Agent) extends IMissingMethodHandler{
	private val log: Logger = LoggerFactory.getLogger(classOf[BelieveDsl])

	def methodMissing(name: String, args: Array[AnyRef]): Unit= {
		if(args.length == 1){
			args.apply(0) match {
				case closure: Closure[Any] =>
					log.info("closure....")
					subject = subject.copy(deductions = subject.deductions + (name -> closure))
					return
				case _ =>
			}
		}
		subject = subject.copy(believes = subject.believes + (name -> args))
	}
}