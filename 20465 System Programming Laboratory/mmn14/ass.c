#include "state.h"
#include "ass.h"

int main (int argc, char* argv[])
{
	if (argc == 1)		/*No files ecxeptable */
	{
		fprintf(stdout, "\nAssembler need at least one file\n");
		return 0;
	}
	int i = 1;
	while (--argc > 0)
	{
		FILE *fp;
		char * filename = argv[i];
		char * filename_as = malloc(sizeof(argv[i])+3);
		strcpy (filename_as, argv[i++]);
		strcat(filename_as,".as");/* the file must ending as ".as". */
		if ((fp = fopen(filename_as,"r")) == NULL)		/*Can't open the file */
		{
			fprintf(stdout,"\nAssembler can't open the file:%s\n", filename_as);
			continue;
		}
		else
		{
			ourstate.IC = 100;
			data_counter = 0;
			symbol_t **symboltable;
			symbolcounter = 0;
			char lineString[MAX_LINE];
			while (fgets(lineString, MAX_LINE, fp) != NULL)
			{
				ourstate.line_num ++;
				ourstate.is_error = line_validation_and_parsing(lineString, ourstate.line_num);
			}
			symboltable = (symbol_t **) malloc(sizeof(symbol_t *)*(symbolcounter));
			add_labels_to_symboltable(symboltable);
			ourstate.is_error = symboltable_validation(symboltable);
			if (ourstate.is_error)
			{
				break;
			}
			addIC(symboltable);
			rewind(fp);
			ourstate.line_num = 0;
			while (fgets(lineString, MAX_LINE, fp) != NULL)
			{
				ourstate.line_num ++;
				addentry(symboltable, lineString);
			}
			if (!ourstate.is_error)
			{
				print_obj(filename, symboltable);
				print_enternals(filename, symboltable);
				print_externals(filename, symboltable);
			}
			fclose(fp);
			free (symboltable);
			free (filename_as);
		}
	}
	return 1;
}
