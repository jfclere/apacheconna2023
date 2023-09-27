hex.o: hex.c hex.h
	gcc -g -c -fpic hex.c
libhex.so: hex.o
	gcc -v -shared -o libhex.so hex.o
main: main.c libhex.so
	gcc -g -c main.c
	gcc -o main main.o -L . -lhex -Wl,-rpath=.
