package nl.jappieklooster.gapl.lib.dsl

import nl.jappieklooster.gapl.lib.model.Agent
import nl.jappieklooster.groovy.meta.IMissingMethodHandler
import org.slf4j.{Logger, LoggerFactory}

class BelieveDsl(var subject:Agent) extends IMissingMethodHandler{
	private val log: Logger = LoggerFactory.getLogger(classOf[BelieveDsl])

	def methodMissing(name: String, args: Array[AnyRef]): Unit= {
		if(subject.believes.contains(name)){
			log.info(s"$name is overwritten")
		}
		subject = subject.copy(believes = subject.believes + (name -> args))
	}
}