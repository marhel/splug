import org.clapper.classutil.ClassFinder
import java.io.File
import com.factor10.plugins._

object HosturApplication {
	def main(args: Array[String]): Unit = {
		println("HosturApplication")
		val classpath = args.map(new File(_))
		val finder = ClassFinder(classpath)
		val classes = finder.getClasses // classes is an Stream[ClassInfo]
		val classMap = ClassFinder.classInfoMap(classes.toIterator) // runs stream out, once
		val plugins = ClassFinder.concreteSubclasses("com.factor10.plugins.FormattingPlugin", classMap)

		val data = Map("some" -> "field")

		plugins.foreach { pluginString => 
			val plugin: FormattingPlugin = Class.forName(pluginString.name).newInstance().asInstanceOf[FormattingPlugin]
			println(s"${plugin.name} (${pluginString.name}): ${plugin.convert(data)}")
		}  
	}
}