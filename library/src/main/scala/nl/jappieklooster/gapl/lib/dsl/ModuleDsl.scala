package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure
import nl.jappieklooster.gapl.lib.model.Agent
import nl.jappieklooster.groovy.MissingMethodInterceptor
import nl.jappieklooster.groovy.meta.IMissingMethodHandler

/**
 * #ModuleDSL
 * The module is the main interface the dsl will communicate with. Its the object
 * behind the script. So the base commands will be delegated to this.
 *
 * The module will decide what dsl to use based on the name of the command.
 * So in reality there are more dsl going on, belevies need to be stored differently
 * than goals for example.
 *
 * Its main purpose is to mutate the subject by choosing the right dsl.
 * @param subject
 */
class ModuleDsl(var subject:Agent) extends ADsl {
	/**
	 * defines  the believes of the agent with help of another dsl
	 * @param commands
	 */
	def believes(commands:Closure[Void]):Unit = {
		val dsl = new BelieveBuildingDsl(subject)
		delegate(commands, wrap(dsl))
		subject = dsl.subject
	}

	/**
	 * defines the goals of the agent with help of another dsl
	 * @param commands
	 */
	def goals(commands:Closure[Void]): Unit ={
		val dsl = new GoalBuildingDsl(subject)
		delegate(commands, wrap(dsl))
		subject = dsl.subject
	}

	/**
	 * The groovy method catching doesn't work with scala for some reason.
	 * therefore the MissingmethodInterceptor will do it for us, which is written
	 * in groovy. This is a workaround.
	 * @param dsl
	 * @return
	 */
	private def wrap(dsl: IMissingMethodHandler):MissingMethodInterceptor = {
		val interceptor = new MissingMethodInterceptor()
		interceptor.setHandler(dsl)
		interceptor
	}
	/**
	 * also execute other modules
	 * @param file
	 */
	def load(file:String): Unit ={
	}

}
