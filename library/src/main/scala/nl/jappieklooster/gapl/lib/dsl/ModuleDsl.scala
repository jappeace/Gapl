package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.model.Agent

class ModuleDsl(var subject:Agent) extends ADsl {
	def believes(commands:Closure[Void]):Unit = {
		val dsl = new BelieveDsl(subject)
		delegate(commands, dsl)
		subject = dsl.subject
	}
}
