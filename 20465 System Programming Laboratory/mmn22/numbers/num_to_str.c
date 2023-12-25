# include "program.h"

/*
This function get numbers and return the value by string
*/

void num_to_str(int n)
{

	char *units [] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	char *exeptions [] = {"ten", "elven", "twelve", "thirteen", "foureen", "fiveteen", "sixteen", "seveteen", "eighteen",
						 "nineteen"};
	char *dosens [] = {"not relevant", "not relevant",  "twentee", "thirtee", "fourtee", "fivetee", "sixtee", "seventee",
						 "eightee", "ninetee"};
	if (n == 0)
	{
		fprintf(foutput, "%s\n", units[0] );
	}
	else if (n/10 == 1)
	{
		switch (n%10)
		{
			case (0):
				fprintf(foutput, "%s\n", exeptions[0]);
				break;
			case (1):
				fprintf(foutput, "%s\n", exeptions[1]);
				break;	
			case (2):
				fprintf(foutput, "%s\n", exeptions[2]);
				break;
			case (3):		
				fprintf(foutput, "%s\n", exeptions[3]);
				break;
			case (4):
				fprintf(foutput, "%s\n", exeptions[4]);
				break;
			case (5):
				fprintf(foutput, "%s\n", exeptions[5]);
				break;
			case (6):
				fprintf(foutput, "%s\n", exeptions[6]);
				break;
			case (7):
				fprintf(foutput, "%s\n", exeptions[7]);
				break;
			case (8):
				fprintf(foutput, "%s\n", exeptions[8]);
				break;
			case (9):
				fprintf(foutput, "%s\n", exeptions[9]);
				break;
		}
	}
	else if (n%10 == 0)
	{
		fprintf(foutput, "%s\n", dosens[n/10] );	
	}
	else if (n <= 10)
	{
		fprintf(foutput, "%s\n", units[n%10] );	
	}
 	else 
	{
		fprintf(foutput, "%s %s\n", dosens[n/10], units[n%10] );
	}

}
