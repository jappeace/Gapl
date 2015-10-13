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
class AgentLoader() {
	val loader = new ScriptLoader()

	/**
	 * load an agent with an existing agent as template, expand the existing agent
	 * @param file
	 * @param agent
	 * @return
	 */
	def load(file:File, agent:Agent):Agent = {
		val module = new ModuleDsl(agent)
		loader.load(file, module)
		return module.subject
	}

	/**
	 * create an agent and load it with the instructions in the file
	 * @param file
	 * @return
	 */
	def load(file:File):Agent = load(file, new Agent())
}
