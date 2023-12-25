myAns:  adjacency.o printMat.o scanMat.o path.o
	gcc -g -ansi -Wall -pedantic adjacency.o printMat.o scanMat.o path.o -o myAns
adjacency.o:  adjacency.c adjacency.h
	gcc -c -ansi -Wall -pedantic adjacency.c -o adjacency.o
printMat.o:  printMat.c adjacency.h
	gcc -c -ansi -Wall -pedantic printMat.c -o printMat.o
scanMat.o:  scanMat.c adjacency.h
	gcc -c -ansi -Wall -pedantic scanMat.c -o scanMat.o
path.o: path.c adjacency.h
	gcc -c -ansi -Wall -pedantic path.c -o path.o
