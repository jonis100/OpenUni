# include "program.h"

FILE *finput;
FILE *foutput;
 
 /*This program get numbers from 0-99 and return the value as string. The input can delivered by kyboard (0 arguments
in a commend line), or by file input (1 arguments in a commend line) or by input  and output file (2 argument in
a commend line representevly)  
*/
 
int main(int argc, char *argv[])
{ 	

	int num;
	if (argc == 1)
	{
		finput = stdin;
		foutput = stdout;
		while (fscanf(stdin,"%d",&num) != EOF)
		{
			num_to_str(num);
		} 	
	}
	else if (argc == 2)
	{
		finput = fopen(argv[1],"r+");
		foutput = stdout;
		if (finput)
		{ 
			while (fscanf(finput,"%d",&num) != EOF)
			{
				num_to_str(num);
			} 	
			fclose(finput);	
		}
		else 
		{
			fprintf(stderr, "Error, can not open file\n");
		}
	}
	else if (argc == 3)
	{
		finput = fopen(argv[1],"r+");
		foutput = fopen(argv[2],"w+");
		if (finput && foutput)
		{ 
			while (fscanf(finput,"%d",&num) != EOF)
			{
				num_to_str(num);
			} 	
			fclose(finput);
			fclose(foutput);
		}
	}
	else
	{
		fprintf(stderr, "Error, too match argument are insert\n");
	}
	return 0;	
}
