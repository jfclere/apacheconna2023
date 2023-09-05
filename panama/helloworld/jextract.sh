export PATH=$JAVA_HOME/bin:/home/jfclere/JAVA/jextract-20/bin:$PATH

# build the classes directly.
#jextract -t org.jfclere -l `pwd`/hex.so hex.h
#/home/jfclere/JAVA/jextract-20/bin/java --source 20 --enable-preview --enable-native-access=ALL-UNNAMED JExtractHelloWorld.java
# java 20 also works
#/home/jfclere/JAVA/jdk-20.0.2/bin/java --source 20 --enable-preview JExtractHelloWorld.java

# in case you build the sources (note we use java 20 to build the classes)
jextract --source -t org.jfclere -l `pwd`/hex.so hex.h
(cd org/jfclere; /home/jfclere/JAVA/jdk-20.0.2/bin/javac --source 20 --enable-preview *.java)
/home/jfclere/JAVA/jdk-20.0.2/bin/java --source 20 --enable-preview JExtractHelloWorld.java
