#define MAX_ARRAY 2000
#define MAX_LINE 80
/*
 * headr file for assembler
 */

int symbolcounter;
int linecounter;
int extern_num;
int data_counter;
state ourstate;
int iteration;

command_line_t *commands_array[MAX_ARRAY];
command_line_t *data_array[MAX_ARRAY];

void debugFirstransition(
		command_line_t *commandsarray[],
		command_line_t *dataarray[],
		symbol_t **symboltable
);

bool line_validation_and_parsing(char line[], int);
void add_labels_to_symboltable(symbol_t  **symboltable);
void addIC(symbol_t **symboltable);
bool symboltable_validation(symbol_t **symboltable);
void addentry(symbol_t ** symboltable, char *line );
void print_enternals(char * namefile, symbol_t ** symboltable);
void print_externals(char * namefile, symbol_t ** symboltable);
void print_obj(char * namefile, symbol_t ** symboltable);
void freeall(symbol_t ** symboltable);

