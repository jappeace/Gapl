package nl.jappieklooster.gapl.lib.model

class Messages(){}

/**
 * Commands for the agents
 */
object Command extends Enumeration{
	type Command = Value
	val Start, Stop = Value
}

/**
 * Agents can update themselves (gives best performance probably, and least
 * buggyness) this way agents won't have to process a backlog for example if
 * a central updater was used and they would be to slow.
 * @param tickNumber
 */
case class Update(tickNumber:Int) {}
