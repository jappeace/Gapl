package nl.jappieklooster.gapl.gui;

import java.nio.file.Paths

import nl.jappieklooster.gapl.Log
import nl.jappieklooster.gapl.lib.controller.MultiAgentController

object Main{
	val log = Log.default()
	val controller = new MultiAgentController(Paths.get("build/resources/main/script"))
	def main(args:Array[String]):Unit = {
		controller.start(this)
	}

	def interact(arg:String): Unit = synchronized {
		log.info(arg)
		controller.stop()
	}
}
