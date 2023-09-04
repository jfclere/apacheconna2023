hex.so: hex.c
	gcc -c -fpic hex.c
	gcc -v -shared -o hex.so hex.o
helloworld: helloworld.c hex.so
	gcc -c helloworld.c
	gcc -o helloworld helloworld.o hex.o
