CC=gcc 
CFLAGS=-Wall -pedantic

universe: universe.o
sequence: sequence.o
maintest: maintest.o

clean:
	rm -f client client.o