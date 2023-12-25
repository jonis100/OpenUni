#include "state.h"
#include "prototypes.h"

/*
 * addEntry.c
 *
 *  add entry to symboltable
 */
void addentry(symbol_t ** symboltable, char *line )
{
	int i;
	int error_flag = 1;
	strArr externline;
	char delimeters [] = {' ','\t', '\n'};

	externline = split(line,delimeters);
	if (*(externline.arr) != NULL && !strcmp(*(externline.arr), ".entry"))
	{
		for (i = 0; i<symbolcounter; i++)
		{
			if (!strcmp(symboltable[i]->_symbolname, externline.arr[1]))
			{
				symboltable[i]->_is_entry = 1;
				error_flag = 0;
			}
		}
		if (error_flag)
		{
			fprintf(stderr, "\n entry %s in line %d does not exist", externline.arr[1], ourstate.line_num);
			ourstate.is_error = 1;
		}
	}
	free(externline.arr);
}

/*
 * this function go over symboltable and print enternals to namefile.ent
 * get namefile and symboltable
 */
void print_enternals(char * namefile, symbol_t ** symboltable)
{
	char namefile_ent [strlen(namefile)+3];
	FILE * entfile;
	int i;
	strcpy(namefile_ent, namefile);
	strcat (namefile_ent, ".ent");
	entfile = fopen(namefile_ent, "w+");
	for (i = 0; i<symbolcounter; i++)
	{
		if (symboltable[i]->_is_entry)
		{
			fprintf(entfile, "\n%s %07d", symboltable[i]->_symbolname, symboltable[i]->_address);
		}
	}
	fclose(entfile);
}

/*
 * this function go over symboltable and print externals to namefile.enx
 * get namefile and symboltable
 */
void print_externals(char * namefile, symbol_t ** symboltable)
{
	char namefile_ext [strlen(namefile)+3];
	FILE * extfile;
	int i,j,k;
	strcpy(namefile_ext, namefile);
	strcat (namefile_ext, ".ext");
	extfile = fopen(namefile_ext, "w+");
	for (i = 0; i<symbolcounter; i++)
	{
		if (symboltable[i]->_is_extern)
		{
			for (j = 0; j<ourstate.IC-100; j++)
			{
				k = 0;
				while (commands_array[j] && commands_array[j]->_operands[k] && k<commands_array[j]->_sum_operands && k<2)
				{
					if(!strcmp(commands_array[j]->_operands[k], symboltable[i]->_symbolname))
						fprintf(extfile, "\n%s %07d", symboltable[i]->_symbolname, commands_array[j]->_address+1+k);
					k++;
				}
			}
		}
	}
	fclose(extfile);
}

int make_code_line(int idx_arr, symbol_t ** symboltable)
{
	int res = 0, tmp_mask;
	char * destopernad;

	tmp_mask = 0;
	tmp_mask = dec_opcode(commands_array[idx_arr]->_command);
	tmp_mask <<= 18;
	res |= tmp_mask;

	if (commands_array[idx_arr]->_sum_operands == 2)
	{
		tmp_mask = 0;
		tmp_mask = dec_source_addressing(commands_array[idx_arr]->_operands[0], symboltable);
		tmp_mask <<= 16;
		res |= tmp_mask;

		tmp_mask = 0;
		tmp_mask = which_register(commands_array[idx_arr]->_operands[0]);
		tmp_mask <<= 13;
		res |= tmp_mask;
		destopernad = commands_array[idx_arr]->_operands[1];
	}
	else
	{
		destopernad = commands_array[idx_arr]->_operands[0];
	}

	tmp_mask = 0;
	tmp_mask = dec_source_addressing(destopernad, symboltable);
	tmp_mask <<= 11;
	res |= tmp_mask;

	tmp_mask = 0;
	tmp_mask = which_register(destopernad);
	tmp_mask <<= 8;
	res |= tmp_mask;

	tmp_mask = 0;
	tmp_mask = dec_funct (commands_array[idx_arr]->_command);
	tmp_mask <<= 3;
	res |= tmp_mask;

	tmp_mask = 1<<2;
	res |= tmp_mask;

	res &= 16777215;

	return res;
}

/*
 * this function get int index of commands array and int represent which
 * operand and encoding it including ARE.
 */
int make_operand_code(int idx_in_arr, int operand_num, symbol_t ** symboltable)
{
	int i, res = 0, tmp_mask = 0;
	unsigned int symboladrress;
	char * operand = commands_array[idx_in_arr]->_operands[operand_num];
	if (operand[0] == '#')	/*  its immidiate number*/
	{
		res = atoi(&(operand[1]));
		/* A from ARE */
		tmp_mask = 1<<2;
	}
	else if (operand[0] == '&')	/*  its relative address of symbol*/
	{
		for (i =0 ; i < symbolcounter ; i++)
		{
			if (!strcmp(&(operand[1]), symboltable[i]->_symbolname))
			{
				res = symboltable[i]->_address - commands_array[idx_in_arr]->_address;
				/* A from ARE */
				tmp_mask = 1<<2;
				break;
			}
		}
	}
	else /* this operand is simple symbol or external*/
	{
		for (i = 0 ; i < symbolcounter ; i++)
		{
			if (!strcmp(operand, symboltable[i]->_symbolname))
			{
				if (symboltable[i]->_is_extern)
				{
					res = 0;
					/* E from ARE */
					tmp_mask = 1;
				}
				else
				{
					symboladrress = symboltable[i]->_address;
					res |= symboladrress;
					/* R from ARE */
					tmp_mask = 1<<1;
				}
				break;
			}
		}
	}
	res <<= 3;
	res |= tmp_mask;
	res &= 16777215;

	return res;
}

void print_obj(char * namefile, symbol_t ** symboltable)
{
	int i, j, address, code_line;
	char namefile_ext [strlen(namefile)+3];
	FILE * obfile;
	strcpy(namefile_ext, namefile);
	strcat (namefile_ext, ".ob");
	obfile = fopen(namefile_ext, "w+");

	fprintf(obfile,"    %d %d    \n", ourstate.IC-100, ourstate.DC);

	for(i = 0 ; i < ourstate.IC-100;) /*go over the lines in IC array*/
	{
		code_line = make_code_line(i, symboltable);
		address = commands_array[i]->_address;
		fprintf(obfile, "%07d %06x\n", address, code_line);

		/*go over all operands till sum_of_oper*/

		for(j = 0 ; j < commands_array[i]->_sum_operands ; j++)
		{
			if (keyWordsValid(commands_array[i]->_operands[j]) != -1)
			{
				code_line = make_operand_code(i, j, symboltable);
				address++;
				fprintf(obfile, "%07d %06x\n", address, code_line);
			}
		}
		i+= commands_array[i]->_sum_words_memory;
	}


	for(i = 0 ; i < data_counter;) /*go over the lines in DC array*/
	{
		address =  data_array[i]->_address + ourstate.IC;
		if (data_array[i]->_command && !strcmp(data_array[i]->_command, ".extern"))
		{
			i++;
		}
		else if (data_array[i]->_is_string)	 /* is string data*/
		{
			char* str = data_array[i]->_operands[0];
			for(j = 0 ; j < strlen(str) ; j++)
			{
				fprintf(obfile, "%07d %06x\n", address, str[j]);
				address++;
			}
			fprintf(obfile, "%07d %06x\n", address, 0);
			address++;
			i+= strlen(str)+1;
		}
		else /* numbers data*/
		{
			int operand;
			for(j = 0 ; j < data_array[i]->_sum_operands ; j++)
			{
				operand = atoi(data_array[i]->_operands[j]);
				operand &= 16777215;
				fprintf(obfile, "%07d %06x\n", address, operand);
				address++;
			}
			i+= data_array[i]->_sum_operands;
		}
	}

	fclose(obfile);
}

