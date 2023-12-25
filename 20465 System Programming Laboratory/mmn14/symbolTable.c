#include "state.h"
#include "prototypes.h"

void add_labels_to_symboltable(symbol_t **symboltable)
{
	int i, j = 0;
	for(i = 0; i<ourstate.IC-START_ADDRESS;i++)
	{
		if (commands_array[i] != NULL)
		{
			/* Adding instructions to symboltable*/
			if (commands_array[i]-> _symbol != NULL)
			{
				symboltable [j] = (symbol_t *) malloc(sizeof(symbol_t));
				symboltable [j] ->_symbolname = (char *)malloc(strlen(commands_array[i]-> _symbol));
				strcpy(symboltable [j] ->_symbolname, commands_array[i]-> _symbol);
				symboltable [j] -> _is_extern = 0;
				symboltable [j] ->_is_command = 1;
				symboltable [j] -> _address = commands_array[i]-> _address;
				symboltable [j] -> _is_entry = 0;
				symboltable [j] -> _line_num = commands_array[i]-> _line_num;
				j++;
			}
		}
	}
	for(i = 0; i<data_counter;i++)
	{

		if (data_array[i] != NULL)
		{
			/* Adding data to symboltable*/
			if (data_array[i]-> _symbol != NULL)
			{
				symboltable [j] = (symbol_t *) malloc(sizeof(symbol_t));
				symboltable [j] ->_symbolname = (char *)malloc(strlen(data_array[i]-> _symbol)+1);
				strcpy(symboltable [j] ->_symbolname, data_array[i]-> _symbol);

				if (data_array[i]->_command && !strcmp(data_array[i]->_command, ".extern"))
				{
					symboltable [j] -> _is_extern = 1;
				}
				else
				{
					symboltable [j] -> _is_extern = 0;
				}
				symboltable [j] ->_is_command = 0;
				symboltable [j] -> _address = data_array[i]-> _address;
				symboltable [j] -> _is_entry = 0;
				symboltable [j] -> _line_num = data_array[i]-> _line_num;
				j++;
			}
			else continue;
		}

	}
}

/*
 *this function add IC for all data in data array except externals  */

void  addIC(symbol_t **symboltable)
{
	int i;
	for (i = 0; i<symbolcounter; i++ )
	{
		if (!symboltable [i]->_is_command && !symboltable [i]->_is_extern )
		{
			symboltable [i]-> _address += ourstate.IC;
		}
	}
}

/*this function check if there is have twice same name of symbol in symboltable.
 * if error return 1 .  no error return 0
 */
bool symboltable_validation(symbol_t **symboltable)
{
	bool is_error = false;
	int i, j;
	for (i = 0; i<symbolcounter; i++ )
	{
		for (j = i +1; j<symbolcounter; j++)
		{
			if (!strcmp(symboltable[i]->_symbolname, symboltable[j]->_symbolname) && !symboltable[i]->_is_extern )
			{
				is_error = true;
				fprintf(stdout,"\nThe name symbol: %s has been used\n",symboltable[i]->_symbolname);
				fprintf(stdout,"\nSymbols in line: %d and: %d \n",symboltable[i]->_line_num, symboltable[j]->_line_num );
			}
		}
	}
	return is_error;
}
