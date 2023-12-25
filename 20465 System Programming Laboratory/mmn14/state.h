#ifndef STATE_H
#define STATE_H
#define START_ADDRESS 100
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

typedef enum bool {false,true} bool;
typedef enum {err,sym,cmd,data,string,extORent} tl;

typedef struct
{
    int IC;
    int DC;
    bool is_error;
    int line_num;
} state;

typedef struct symbol
{
	char *_symbolname;
	bool _is_extern;
	int _address;
	bool _is_command; /*If false is directive */
	bool _is_entry;
	int _line_num;
} symbol_t;

typedef struct command_line{
	int _address;
	int _line_num;
	char* _symbol;
	char* _command;
	bool _is_command; /* true if is *command* false if is *directive*/
	int _sum_operands;
	int _sum_words_memory;/*the vlidation not care this*/
	bool _is_string;/*if data is string*/
	char ** _operands;
} command_line_t;

typedef struct
{
    char* name;
    int func;
    int opcode;
    int sum_operands;
} func;

typedef struct 
{
    char ** arr;
    int len;
} strArr;

#endif
