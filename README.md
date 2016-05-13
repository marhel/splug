# splug - a Scala Plugin Experiment

I wanted to test out how a host and plugins could be dynamically composed. I didn't want to add the plugins as an explicit dependency to the host, or set up a potentially complex sbt multiproject. I'd rather just add the plugin jars to the classpath. Then the host should be able to discover concrete implementations of the required plugin trait on its classpath.

In effect, I want to compose the application parts at deploy-time, but they do not need to be _dynamically_ discoverable (i.e. adding plugins after the application has been started).  

In this case, clone the repo, run `./build.sh` followed by `./run-host xml json`. This will build host and plugins, package them as jars, execute the host application, which will call into the xml and/or json plugins. Note that the host application is packaged into a fatjar, via the [sbt-assembly](https://github.com/sbt/sbt-assembly) plugin.

Neither application, nor plugins do anything remotely interesting, they only serve to show the plugin system.

    .
    +--hostur   - the host application
    +--plugins  
       +--json  - the json plugin
       +--xml   - the xml plugin
       
The thing I'm not too pleased about, is the fact that the formattingPlugin.scala is copied to each plugin, but otherwise neither host nor any of the plugins depends on each other.

The only interesting parts of the program is [Application.scala](hostur/src/main/scala/Application.scala), which is inspired by [this post](https://vikashazrati.wordpress.com/2011/09/15/building-a-plugin-based-architecture-in-scala/)

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
