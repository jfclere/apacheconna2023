export JAVA_HOME=/home/jfclere/JAVA/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
export LD_LIBRARY_PATH=`pwd`
javac --source 21 --enable-preview Hex.java
java --source 21 --enable-preview HelloWorld.java
