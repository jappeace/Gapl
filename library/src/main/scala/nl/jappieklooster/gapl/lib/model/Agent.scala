package nl.jappieklooster.gapl.lib.model

/**
 * believes are just data
 * deductions is data derived from the beleives
 */
case class Agent(
	believes : Map[String, Iterable[Any]]
) {
	def this() = this(Map())
}
