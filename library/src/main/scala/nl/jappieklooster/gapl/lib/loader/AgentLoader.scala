package nl.jappieklooster.gapl.lib.loader

import java.io.File

import nl.jappieklooster.gapl.lib.dsl.ModuleDsl
import nl.jappieklooster.gapl.lib.model.Agent


/**
 * Agent loader, created targeted at an environment (something the agent can
 * interact with, (weakly typed because groovy)).
 *
 * put in a path to a file
 * @param environment
 */
class AgentLoader(environment: AnyRef) {
	val loader = new ScriptLoader()

	def load(file:File):Agent = {
		val result = new Agent()
		val module = new ModuleDsl(result)
		loader.load(file, module)
		return module.subject
	}
}
