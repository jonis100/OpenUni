#define MAX 100

extern int linecounter;
extern state ourstate;
extern command_line_t *commands_array[];
extern command_line_t *data_array[];
extern symbol_t *symboltable [];
extern int symbolcounter;
extern int extern_num;
extern int data_counter;

void addIC(symbol_t **symboltable);
bool symboltable_validation(symbol_t **symboltable);
void add_labels_to_symboltable(symbol_t  **symboltable);
int words_memory_counter(int ,bool , bool, char* operands[]);
void store_as_struct(
		char* symbol,
		char* command,
		int sum_operands,
		bool is_command,
		bool is_string,
		char* operands[]
);
void debugFirstransition(
		command_line_t *commandsarray[],
		command_line_t *dataarray[],
		symbol_t **symboltable
);

char * fillzeroto7(int x);
int dec_opcode(char * command);
int dec_source_addressing(char * operand, symbol_t ** symboltable);
int which_register(char * reg);
int dec_funct(char * command);
void addentry(symbol_t ** symboltable, char *line );
void print_externals(char * namefile, symbol_t ** symboltable);
void print_enternals(char * namefile, symbol_t ** symboltable);
int make_code_line(int idx_arr, symbol_t ** symboltable);
int make_operand_code(int idx_in_arr, int operand_num, symbol_t ** symboltable);
void print_obj(char * namefile, symbol_t ** symboltable);


bool line_validation_and_parsing(char line[], int line_num);
strArr split(char str[],char delimeter[]);
bool symbolValid(char *word);
int keyWordsValid(char word[]);
int invalidChars(char *word);
char * removeCommas(char *word);



char *scat(char *s,char *t);
void debug_add_to_line_struct(
		char* symbol,
		char* command,
		int sum_operands,
		bool is_command,
		bool is_string,
		char* operands[MAX]
);
