# Example for FFM upcall

To run the example adjust the java location and run:
```./java.sh```

The example is made of the following
hex.c: a C code file:
It is compiled in a native library libhex.so, there 2 routines:
one routine that convert upcase letters in lower and lowercase in upcase.
and the other one is a callback setter that will allow to call the java method in Hex.java

Hex.java: a java code file
2 methods, one to use UpperCase() in java, the other one for be called from the C code to call the first method.

HelloWord.java:
The main java code:
It calls the C function without the setting the callback, the C function is called:
```default callback: hELLO wORLD! pANAMA STYLE`
It sets the callback and calls the C function that calls the java code in Hex.java:
```java callback: HELLO WORLD! PANAMA STYLE```
