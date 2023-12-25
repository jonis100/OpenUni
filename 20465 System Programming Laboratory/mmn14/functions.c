#include "state.h"
#include "prototypes.h"

func functions[] = {
    {"mov",0,0,2},
    {"cmp",0,1,2},
    {"add",1,2,2},
    {"sub",2,2,2},
    {"lea",0,4,2},
    {"clr",1,5,1},
    {"not",2,5,1},
    {"inc",3,5,1},
    {"dec",4,5,1},
    {"jmp",1,9,1},
    {"bne",2,9,1},
    {"jsr",3,9,1},
    {"red",0,12,1},
    {"prn",0,13,1},
    {"rts",0,14,0},
    {"stop",0,15,0}
};

char *registers[] = {"r0","r1","r2","r3","r4","r5","r6","r7"};

char *directives[] = {".data",".string",".entry",".extern"};

void debug_add_to_line_struct(
    char* symbol,
    char* command,
    int sum_operands,
    bool is_command,
    bool is_string,
    char* operands[100]
)
{
    int i;
    printf("\nstruct \nnnnnnnnnnnnnnnnnnnnnnnnnnn\n{\n");
    printf("\t char* symbol:%s;\n",symbol);
    printf("\t char* command:%s;\n",command);
    printf("\t int sum_operands:%d;\n",sum_operands);
    printf("\t bool is_command:%d;\n",is_command);
    printf("\t bool is_string:%d;\n",is_string);
    printf("\t char* operands[100]: {");
    for(i = 0; i < sum_operands; i++) printf("%s, ", operands[i]);
    printf("};\n");
    printf("} command_line;\n");

}
/**
 * check if there is some 
 * unnecessary charaters
 * befor and after the word
 * */
int invalidChars(char *word)
{
    char delimeter[] = {',',':','.','#','&','\"','\0'};
    int i = 0;
    while (delimeter[i]!='\0')
    {
        if((strchr (word,delimeter[i])-word+1)>0) return i;
        i++;
    }
    return -1;
}

/* 
return <-2 if is some directive command
return -2 if is not keyWord
return -1 if word eq register name
return n>=0 if word eq function name 
n is sum_operands
*/
int keyWordsValid(char word[])
{
    int i,j=0;
    int type = -2;
    int registers_len = sizeof(registers) / sizeof(registers[0]);
    int functions_len = sizeof(functions) / sizeof(functions[0]);
    int directives_len = sizeof(directives) / sizeof(directives[0]);
    char * delimeter = ",\t .\n";
    char ** words = split(word,delimeter).arr;
    for (;words[j]!=NULL;j++)
    {
        for(i=0;i<registers_len;i++) 
            if(strcmp(registers[i],word)==0) 
                return -1;
        for(i=0;i<functions_len;i++) 
            if(strcmp(functions[i].name,word)==0) 
                return functions[i].sum_operands;
        for(i=0;i<directives_len;i++) 
            if(strcmp(directives[i],word)==0) 
                return i-6;
    }
	free(words);
    return type;
}

char *scat(char *s,char *t)
{
    char *p=malloc(strlen(s)+strlen(t)+1);    /* 3: you will have to reserve memory to hold the copy. */
    int ptr =0, temp = 0;                   /* 4 initialise some helpers */

    while(s[temp]!='\0'){                  /* 5. use the temp to "walk" over string 1 */
        p[ptr++] = s[temp++];
    }
    temp=0;
    while(t[temp]!='\0'){                   /* and string two */
        p[ptr++]=t[temp++];
    }
    return p;
}
strArr split(char str[],char delimeter[])
{
    strArr res;
    char ** arr = malloc(2 * sizeof(char*));
    int i=0;
    char * word = strtok (str,delimeter);
    if(arr == NULL)                     
    {
        fprintf( stderr,"Error! memory not allocated.");
        exit(0);
    }
    while (word != NULL)
    {
        arr[i] = malloc(2 * sizeof(char));
        if(arr[i] == NULL)                     
        {
            fprintf( stderr,"Error! memory not allocated.");
            exit(0);
        }
        arr[i]=word;
        word = strtok (NULL,delimeter);
        i++;
    }
    res.arr = arr;
    res.len = i;
    return res;
}
/*
 * dec_opcode(word from struct)
 */
int dec_opcode(char * command)
{
	int i, res;
	int num_of_commands = 16;
	char * commands[] ={
			"mov",
			"cmp",
			"add",
			"sub",
			"lea",
			"clr",
			"not",
			"inc",
			"dec",
			"jmp",
			"bne",
			"jsr",
			"red",
			"prn",
			"rts",
			"stop"
	};
	int commands_code [] ={
			0,
			1,
			2, 2,
			4,
			5, 5, 5, 5,
			9, 9, 9,
			12,
			13,
			14,
			15
	};
	for (i = 0 ; i < num_of_commands; i++)
	{
		if (command && !strcmp(command, commands[i]))
		{
			res = commands_code[i];
		}
	}
	return res;
}

/*
 * dec_source_addressing(word from struct
 */
int dec_source_addressing(char * operand, symbol_t ** symboltable)
{
	if (operand)
	{
		int i = 0;
		if (operand[0] == '#') 	/*immidetly  addressing */
		{
			return 0;
		}
		else if (keyWordsValid(operand) == -1)  	/*immidetly  register addressing */
		{
			return 3;
		}
		else if (operand[0] == '&') 	/*relative   addressing */
		{
			return 2;
		}
		else if (keyWordsValid(operand) == -2) /*is number or symbol CHECK UNSIGNED NUMBER!!! p23 */
		{
			for (i = 0 ; i < symbolcounter ; i++)
			{
				if (operand && !strcmp(operand, symboltable[i]->_symbolname))
				{
					return 1;
				}
			}
		}
	}
	return 0;
}

/*
 *  which register
 *  get chr * return int represent register or 0 if is not register
 */
int which_register(char * reg)
{
	int i, num_of_registers = 8;
	char * registers_name [] = {
		"r0",
		"r1",
		"r2",
		"r3",
		"r4",
		"r5",
		"r6",
		"r7"
	};

	for (i = 0; i < num_of_registers ; i++)
	{
		if (registers_name[i] && reg && !strcmp(reg, registers_name[i]))
		{
			return i;
		}
	}
	return 0;
}

/*
 * dec_funct(word command  from struct)
 */
int dec_funct(char * command)
{
	int i, res;
	int num_of_commands = 16;
	char * commands[] ={
			"mov",
			"cmp",
			"add",
			"sub",
			"lea",
			"clr",
			"not",
			"inc",
			"dec",
			"jmp",
			"bne",
			"jsr",
			"red",
			"prn",
			"rts",
			"stop"
	};
	int funct_code [] ={
			0,0,
			1,
			2,
			0,
			1,
			2,
			3,
			4,
			1,
			2,
			3,
			0, 0, 0, 0
	};
	for (i = 0 ; i < num_of_commands ; i++)
	{
		if (command && !strcmp(command, commands[i]))
		{
			res = funct_code[i];
		}
	}
	return res;
}

/*
 * This function free all memory allocations in this program
 */
void freeall(symbol_t ** symboltable)
{
	int i = 0, j = 0;

	/*
	 * free command_array
	 */
	printf("\nourstate.IC-100 %d", ourstate.IC-100);
	for (;i<ourstate.IC-100 && commands_array[i] ;)
	{
		if (commands_array[i]->_symbol)
		{
			free(commands_array[i]->_symbol);
		}
		if (commands_array[i]->_command)
		{
			free(commands_array[i]->_command);
		}
		
		for (j =0;j<commands_array[i]->_sum_operands;)
		{

			{
				free(commands_array[i]->_operands[j]);
			}
			j++;
		}
		if (commands_array[i]->_operands)
		{
			free(commands_array[i]->_operands);
		}
		free (commands_array[i]);
		i++;

	}

	/*
	 * free data_array
	 */
	i =  0; j = 0;
	for (;i<ourstate.DC && data_array[i];)
	{
		if (data_array[i]->_symbol)
		{
			free(data_array[i]->_symbol);
		}

		if (data_array[i]->_command)
		{
			free(data_array[i]->_command);
		}

		while(data_array[i]->_operands[j])
		{
			free(data_array[i]->_operands[j]);
			j++;
		}
		if (data_array[i]->_operands)
		{
			free(data_array[i]->_operands);
		}
		free (data_array[i]);
		i++;
	}

	/*
	 * free symboltable
	 */
	i = 0;
	for (;i<symbolcounter;)
	{
		if (symboltable [i]->_symbolname)

		{
			free (symboltable [i]->_symbolname);
		}
		free (symboltable [i]);
		i++;
	}
	free (symboltable);
}
