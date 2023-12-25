numbers: numbers.o num_to_str.o
	gcc -g -ansi -Wall -pedantic numbers.o num_to_str.o -o numbers
numbers.o: numbers.c program.h
	gcc -c -ansi -Wall -pedantic numbers.c -o numbers.o
num_to_str.o: num_to_str.c program.h
	gcc -c -ansi -Wall -pedantic num_to_str.c -o num_to_str.o

