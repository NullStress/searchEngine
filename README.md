The project uses java 8.

To build the project from source make sure that JAVA_HOME points to a JDK 8 installation.
Then run on your commandline in the project root directory:

gradlew clean build

This will generate class files under {project root directory}/build/classes
and the executable jar under {project root directory}/build/libs

To run the jar type: java -jar {path to jar} {directory with text files}

The supplied directory has to be a directory and the application requires files that can be read with UTF-8 encoding