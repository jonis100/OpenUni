#include <stdio.h>
#include <string.h>
#define MAXIMUM_STRING_SIZE 100

/* This function get tow strings pattern and text, and return the best position (index of substirg) with less mismatches */

int string_matching (char pattern[] , char text[])
{
	int i,j, counter = 0;
	int minMismatch = 100;
	int index = 0;
	for (j = 0; j < strlen(text); j++)/* count the mismatches from each position*/
	{
		for (i =0; i < strlen(pattern); i++)
		{	
			if ((text+j)[i] != pattern[i])
			{
				counter += 1;
			}	
		}
		if (counter < minMismatch)
		{
			minMismatch = counter;
			index = j;/*save the index of position of minimum mismatch*/
		}
		counter = 0;	
	}
	return index;
	
}

int main()
{
	char pattern[MAXIMUM_STRING_SIZE];
	char text[MAXIMUM_STRING_SIZE];
	printf ("\n please insert strings for pattern\n");
	gets(pattern);
	puts ("\n please insert strings for text\n");
	gets(text);
	printf("\nyour pattern is: %s", pattern);
	printf(", and your text is: %s", text);
	printf ("\nThis is the pattern string: %s, And this is text string: %s.\n"
	"The best position is %d \n", pattern, text, string_matching (pattern, text));   
	return 0;
}
