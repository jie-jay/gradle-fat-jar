# gradle-fat-jar

This repository is a boilerplate for easily creating uber jars, as well as sample code for teaching COMP90015 tutorial. 
# Week 1
## What is a fat jar(Uber jar)?
Essentially, it's an archive(compressed) file packed with all dependencies required to run a java application, therefore end-users don't have to download them manually.  
The manifest file tells java what (default) main class to execute in the uber jar.

## How to build an uber jar?
We use [Gradle](https://gradle.org/) dependency management in this repository and a plugin called [shadowJar by John Engelman](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow), building an uber jar is simple as:
```shell
./gradlew build
./gradlew shadowJar 
```
If the process is successful, the uber jar is located at build/libs/fatjarapp-0.1.0-all.jar.  
It can be executed with:  
```shell
java -jar build/libs/fatjarapp-0.1.0-all.jar
```  

If you are familiar with Maven, you can use Maven [Shade](https://maven.apache.org/plugins/maven-shade-plugin/) plugin as well.  
For command line parser, use java scanner class or [Apache common CLI](https://commons.apache.org/proper/commons-cli/)  
Don't forget to change package name, app name, version, etc. if you adopt this repository.

# Week 2
Simple TCP/UDP example program located at [au.edu.unimelb.ds.week2](https://github.com/jie-jay/gradle-fat-jar/tree/master/src/main/java/au/edu/unimelb/ds/week2).  
To run the demo applications, build an uber jar and run with:
```shell
java -cp build/libs/fatjarapp-0.1.0-all.jar au.edu.unimelb.ds.week2.UDPServer
java -cp build/libs/fatjarapp-0.1.0-all.jar au.edu.unimelb.ds.week2.UDPServer
```
## Why we need the full classname?
Because in the uber jar, there is only one main class given in the manifest.  
You'll need to tell java where the class is located (by using classpath/cp parameter), and the full class name for your application's main method.

Please feel free to send pull requests if you find any issue or have recommendation for improving the code.

---
Maintainer: zhao dot j4 at student dot unimelb dot edu dot au  
License: [The Unlicense](https://unlicense.org/) - Use it whatever and however you like.  
Disclaimer: This repository may contain code from various authors, books, code repos, we are using it solely for teaching and education purpose.  
If any right holder doesn't agree the code to be released publicly in this repository, please contact me for removal. 
