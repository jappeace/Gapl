package nl.jappieklooster.gapl.gui;

import java.io.File

import nl.jappieklooster.gapl.lib.loader.AgentLoader
object Main{
	def main(args:Array[String]):Unit = {
		val loader = new AgentLoader(null)
		println(loader.load(new File("build/resources/main/script/sally.gdsl")))
	}
}
