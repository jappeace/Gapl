package nl.jappieklooster.gapl

import org.slf4j.LoggerFactory

import scala.reflect.ClassTag

package object lib {

}

/**
 * Shorthand
 */
object Log{
	def default() = LoggerFactory.getLogger(this.getClass.getSimpleName)
	def get[T] = LoggerFactory.getLogger(classOf[ClassTag[T]])
}