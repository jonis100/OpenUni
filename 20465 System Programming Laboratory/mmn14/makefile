all: ass

ass: ass.o scan1.o list.o functions.o symbolTable.o secondTransition.o
	gcc -Wall -g ass.o scan1.o functions.o list.o symbolTable.o secondTransition.o -lm -o ass

ass.o: ass.c state.h
	gcc -Wall -c -pedantic ass.c -o ass.o

scan1.o: scan1.c state.h prototypes.h
	gcc -Wall -c -pedantic scan1.c -o scan1.o
	
functions.o: functions.c state.h prototypes.h
	gcc -Wall -c -pedantic functions.c -o functions.o
	
list.o: list.c functions.c state.h prototypes.h 
	gcc -Wall -c -pedantic list.c -o list.o
	
symbolTable.o: symbolTable.c functions.c state.h prototypes.h 
	gcc -Wall -c -pedantic symbolTable.c -o symbolTable.o
	
secondTransition.o: secondTransition.c functions.c state.h prototypes.h 
	gcc -Wall -c -pedantic secondTransition.c -o secondTransition.o