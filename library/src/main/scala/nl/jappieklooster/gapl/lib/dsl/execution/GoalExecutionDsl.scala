package nl.jappieklooster.gapl.lib.dsl.execution

import nl.jappieklooster.gapl.lib.model.Agent

/**
 *
 * @param agent
 * @param environment the environment which handles synchronization
 */
class GoalExecutionDsl(agent:Agent, val environment:AnyRef) extends BelieveExecutionDsl(agent){
}
