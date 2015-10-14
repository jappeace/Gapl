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
	def getBelieves():Expando = new Expando(agent.believes)
}
