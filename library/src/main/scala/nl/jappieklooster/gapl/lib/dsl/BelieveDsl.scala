package nl.jappieklooster.gapl.lib.dsl

import nl.jappieklooster.gapl.lib.model.Agent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
	import nl.jappieklooster.groovy.meta.IMissingMethodHandler

class BelieveDsl(var subject:Agent) extends IMissingMethodHandler{
	private val log: Logger = LoggerFactory.getLogger(classOf[BelieveDsl])

	override def methodMissing(name: String, args: Array[AnyRef]): Unit = {
		subject = subject.copy(believes = subject.believes + (name -> args))
	}


}