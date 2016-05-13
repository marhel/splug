CLASSPATH=hostur/target/scala-2.11/hostur-assembly-1.0.jar
if [ $# -eq 0 ]; then
	echo "$0 <plugins>
where plugins is either or both of json or xml"
	exit 1
fi

while [ $# -gt 0 ]; do
	PLUGIN_JAR=plugins/$1/target/scala-2.11/$1-plugin_2.11-1.0.jar
	CLASSPATH=$CLASSPATH:$PLUGIN_JAR
	shift
done
java -cp $CLASSPATH HosturApplication
