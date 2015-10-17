package nl.jappieklooster.gapl.lib.dsl.execution

import groovy.util.Expando
import nl.jappieklooster.gapl.lib.dsl.Delegator
import nl.jappieklooster.gapl.lib.model.Agent
import scala.collection.JavaConversions._

/**
 * the purpouse of this class is to add the believes properties constructed
 * in the believe loading (with help of overlaoding the missingmethod handlers).
 * @param agent
 */
class BelieveExecutionDsl(agent:Agent) extends Delegator{
	/**
	 * This will make believes available as expando
	 * The expendo is a modifyable copy of the agents believes
	 * The agent should be updated with the modified believes in the
	 * expando after using this executor
	 * @return
	 */
	val believes = new Expando(agent.believes)
}
