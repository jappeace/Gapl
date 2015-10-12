package nl.jappieklooster.gapl.lib.model

import groovy.lang.Closure

/**
 * believes are just data
 * deductions is data derived from the beleives
 */
case class Agent(
	believes : Map[String, Iterable[Any]],
	deductions :Map[String, Closure[Any]]
) {
	def this() = this(Map(), Map())
}
