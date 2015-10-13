package nl.jappieklooster.groovy
import nl.jappieklooster.groovy.meta.IMissingMethodHandler

class MissingMethodInterceptor {
	IMissingMethodHandler handler
	def methodMissing(String name, args){
		handler.methodMissing(name, args)
	}
}
