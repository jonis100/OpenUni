#include "state.h"
#include "prototypes.h"


/*
 * This file contain functions in order to get command line as split string, and make a list
 * of structs contain order information, sorted. code zone first. data zone  second.
 */


void store_as_struct(char* symbol, char* command, int sum_operands, bool is_command,
		bool is_string, char* operands[])
{

	int i = 0;
	command_line_t *new_command = (command_line_t *)malloc(sizeof(command_line_t));
	if (command && !strcmp(command, ".entry"))
	{
		return;
	}
	if (symbol != NULL) /*make sure if isn't symbol it NULL*/
	{
		new_command->_symbol = (char *)malloc (sizeof(char)*(strlen(symbol)+1));
		strcpy(new_command->_symbol, symbol);
		symbolcounter++;
	}
	if (command != NULL)
	{
		new_command->_command = (char *)malloc (sizeof(char)*(strlen(command)+1));
		strcpy(new_command->_command, command);
	}

	new_command->_address = is_command? ourstate.IC: ourstate.DC;

	new_command->_is_command = is_command;
	new_command->_operands = (char **)malloc(sizeof(char *)*(sum_operands));
	for (i = 0; i < sum_operands; i++)
	{
		new_command->_operands[i] = (char *)malloc(sizeof(char)*(strlen(operands[i])+1));
		strcpy(new_command->_operands[i], operands[i]);
	}
	new_command->_sum_operands = sum_operands;
	new_command->_sum_words_memory = words_memory_counter(sum_operands, is_string, is_command, operands);
	new_command->_is_string = is_string;
	new_command->_line_num = ourstate.line_num;


	/*Insert instructions to IC array*/
	if (is_command)
	{
		commands_array[ourstate.IC-START_ADDRESS] = new_command;
		ourstate.IC += new_command->_sum_words_memory;
	}

	/*Insert instructions to DC array*/

	else /* is data*/
	{
		if (command && !strcmp(command, ".extern"))		 /* is external no counted in DC and address 0*/
		{
			new_command->_address = 0;
			data_array[data_counter] = new_command;
			data_counter++;
		}
		else		 /* is data counted in DC*/
			{
			data_array[data_counter] = new_command;
			data_counter += new_command->_sum_words_memory;
			ourstate.DC += new_command->_sum_words_memory;
			}

	}
}

int words_memory_counter(int sum_operands,bool is_string, bool is_command,  char* operands[])
{
	int i, res;

	res = 1;
	for (i=0;i<sum_operands;i++)
	{
		if (keyWordsValid(operands[i]) == -2) /*is number or  symbol */
		{
			res += is_string ? strlen(operands[i]): 1;
		}
		if (keyWordsValid(operands[i])==-1) /* if this is register. NO NEEDED*/
		{
			continue;
		}
	}
	if (!is_string && !is_command) /*is data no string so no need word memory for command */
	{
		res -- ;
	}
	return res;
}
void debugFirstransition(
		command_line_t *commandsarray[],
		command_line_t *dataarray[],
		symbol_t **symboltable)
{

	int j;
	printf("\n======print commandsarray=====\n");
	for(j = 0; j<ourstate.IC-START_ADDRESS;j++)
	{
		if (commandsarray[j] != NULL)
		{
			int i;
			printf("\nstruct \n{\n");
			printf("\t int address:%d;\n",commandsarray[j]->_address);
			printf("\t char* symbol:%s;\n",commandsarray[j]->_symbol);
			printf("\t char* command:%s;\n",commandsarray[j]->_command);
			printf("\t int words in memorey:%d;\n",commandsarray[j]->_sum_words_memory);
			printf("\t int sum_operands:%d;\n",commandsarray[j]->_sum_operands);
			printf("\t bool is_command:%d;\n",commandsarray[j]->_is_command);
			printf("\t bool is_string:%d;\n",commandsarray[j]->_is_string);
			printf("\t char* operands[100]: {");
			for(i = 0; i < commandsarray[j]->_sum_operands; i++) printf("%s, ", commandsarray[j]->_operands[i]);
			printf("\n line_num is:%d;\n",commandsarray[j]->_line_num);
			printf("};\n");
			printf("} command_line;\n");
		}
		else printf("\nmemory word  1 saved for second transiton \n");
	}

		printf("\n======print data array=====\n");
		for(j = 0; j<data_counter;j++)
		{
			if (data_array[j] != NULL)
			{
				int i;
				printf("\nstruct \n{\n");
				printf("\t int address:%d;\n",data_array[j]->_address);
				printf("\t char* symbol:%s;\n",data_array[j]->_symbol);
				printf("\t char* command:%s;\n",data_array[j]->_command);
				printf("\t int words in memorey:%d;\n",data_array[j]->_sum_words_memory);
				printf("\t int sum_operands:%d;\n",data_array[j]->_sum_operands);
				printf("\t bool is_command:%d;\n",data_array[j]->_is_command);
				printf("\t bool is_string:%d;\n",data_array[j]->_is_string);
				printf("\t char* operands[100]: {");
				for(i = 0; i < data_array[j]->_sum_operands; i++) printf("%s, ", data_array[j]->_operands[i]);
				printf("\n line_num is:%d;\n",data_array[j]->_line_num);
				printf("};\n");
				printf("} command_line;\n");
			}
			else printf("\nmemory word  1 saved for second transiton \n");
		}
		printf("\n======print symboltable=====\n");
		for(j = 0; j<symbolcounter;j++)
		{
			if (symboltable [j] != NULL)
			{
				printf("\nstruct \n{\n");
				printf("\t int address:%d;\n",symboltable[j]->_address);
				printf("\t char* _symbolname:%s;\n",symboltable[j]->_symbolname);
				printf("\t char* _is_extern:%d;\n",symboltable[j]->_is_extern);
				printf("\t bool is_command:%d;\n",symboltable[j]->_is_command);
				printf("\t bool _is_entry:%d;\n",symboltable[j]->_is_entry);
				printf("\t line_num is:%d;\n",symboltable[j]->_line_num);
				printf("} symbol_t;\n");

			}
			else printf("\nBAD NULL nmemory word  1 saved for second transiton \n");
		}

}


