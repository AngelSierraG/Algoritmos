#Makefile en construccion aun

CC=gcc 
CFLAGS=-Wall

#universe: universe.o
#sequence: sequence.o
#maintest: maintest.o
main: main.o

clean:
	rm -f main.o