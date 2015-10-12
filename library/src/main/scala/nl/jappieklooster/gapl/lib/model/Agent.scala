package nl.jappieklooster.gapl.lib.model

/**
 * whatever the hell an agent is, its lke an agent.
 */
case class Agent(believes : Map[String, Seq[Any]]) {
	def this() = this(Map())
}
