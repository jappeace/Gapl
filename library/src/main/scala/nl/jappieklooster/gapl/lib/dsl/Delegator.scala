package nl.jappieklooster.gapl.lib.dsl

import groovy.lang.Closure

/**
 * just a base class
 *
 * remember all public methods will be accessible in the script.
 * this is why its better to make a separate dsl class to handle *.dsl files, you can modify all weird user input in these
 * classes to something proper. For example: it is much easier for a user to specify the type of a Pokemon as a string
 * However it is in code better to have it as a enumeration, so you receive a string in the dsl class and change it then
 * to the correct enum, if not available, log a warning. (We assume the user has access to the logs)
 *
 * dsl classes are more than often error handlers than anything else, make sure to log any user fuckups so he can learn
 * And don't trust the input
 */
trait Delegator {
	/**
	 * call the closure with the object as delegate
	 * @param commands
	 * @param to
	 */
	protected def delegate[T](commands: Closure[T], to: AnyRef): T = {
		commands.setDelegate(to)
		commands.setResolveStrategy(Closure.DELEGATE_FIRST)
		commands.call
	}
}