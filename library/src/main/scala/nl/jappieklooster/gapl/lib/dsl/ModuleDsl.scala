package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.model.Agent
import nl.jappieklooster.groovy.MissingMethodInterceptor

class ModuleDsl(var subject:Agent) extends ADsl {
	def believes(commands:Closure[Void]):Unit = {
		val dsl = new BelieveDsl(subject)
		val interceptor = new MissingMethodInterceptor()
		interceptor.setHandler(dsl)
		delegate(commands, interceptor)
		subject = dsl.subject
	}
	def goals(commands:Closure[Void]): Unit ={
	}

}
