#include "state.h"
#include "prototypes.h"

bool line_validation_and_parsing(char line[], int line_num) 
{
    /***
     * check commas
     */
    int sum_commas = 0,
        x = 0;
    while (x<strlen(line))
        if(line[x++] == ',') sum_commas++;
    /* the line as array of words */
    strArr res = split(line," \t\n,");
    char ** words = res.arr;
    int len = res.len;

    /* data for line struct */
    char *symbol = NULL;
    char *command = NULL;
    char* operands[100];
    bool is_command_struct=false;
    bool is_string_struct = false;
    int sum_operands = 0;/*commas validation and struct */
    bool is_data_stored =false; 
    
    char *error[] = {
        "is not a number",/*0*/
        "contain invalid characters",/*1*/
        "is saved word",/*2*/
        "is not command",/*3*/
        "is invalid symbol",/*4*/
        "contain too match commas",/*5*/
        "\".string\" data type need to be within quotes (\")",/*6*/
        "there is no open quotes",/*7*/
        "there is no close quotes",/*8*/
        "less command or symbol",/*9*/
        "there is no operands",/*10*/
        "there is too mutch operands",/*11*/
        "less opernd",/*12*/
        "less comma",/*13*/
        "the symbol is too long"/*14*/
        ""/*15*/
    };

    tl type_line = err;/* is a command, directive or string */
    char* prev_word = NULL;
    bool s_quotes = false,
        e_quotes = false,
        line_error = false,
        skip = false;
    int operands_index = 0,
        error_num = 15,/* print the relevant error */
        error_printed = 0, /* for unduplicated messeges */
        i = 0;

    while (i<len)
    {
        char *word = words[i];
        bool is_command=false,
            is_symbol=false,
            is_data = false,
            is_string=false,
            is_error=false,
            is_extORent=false;

        /* word type */
        int type = 0;
        char ch = word[0];
        bool Immediate_number = (ch== '#' && type_line==cmd);
        /* first char is alpha */
        if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '\n') || type_line==string) 
        {
            if(type_line==data){
                int c = 0;
                for (;c<strlen(word); c++){
                    if (!isdigit(word[c]) && strchr( ",.-+", word[c] )==NULL){
                        error_num = 0;
                        is_error = true;
                        break;
                    }
                    else is_error = false;
                }
            }
            else if(type_line==extORent || type_line!=string) 
            {
                error_num = 1;
                is_error = true;
            }
            else is_error = false;
            /* may be is operands or data */
            if(strchr( ",.-+", ch )!=NULL || Immediate_number) is_error=false;
            else if(type_line==string)
            {
                if(ch=='\"') 
                {
                    s_quotes = true;
                    is_error = false;
                }
                if(word[strlen(word)-1]=='"')
                {
                    e_quotes = true;
                    is_error = false;
                }
            }
            else if
            (
                type_line==cmd && 
                (
                    strcmp(command,"bne")==0 || 
                    strcmp(command,"jmp")==0 || 
                    strcmp(command,"jsr")==0) 
                && ch=='&'
            ){
                is_error = false;
            } 
        }
        if(ch == ';' || (strcmp(word,"\n")==0 && prev_word == NULL && type_line==err))
        {
            skip = true;
            is_error = false;
            return true;/* is comment or white line*/
        }
        /* symbol validation */
        if(type_line!=string && type_line!=data) 
        {
            int l = strlen(word);
            if(type_line!=sym && word[l-1] == ':' && l<=31)
            {
                char *p = word;
                /* cut the ":" from end of the label for keywords validation */
                p[strlen(p)-1] = 0;
                symbol = word;
                type_line=sym;
            }
            else if((type_line==cmd || type_line==extORent) && l>31){
                is_error = true;
                error_num = 14;
            }
            else if(type==-2 && type_line!= extORent){
                is_error = true;
                error_num = 4;
            }
        }
        if(Immediate_number)
        {
            int c=0;
            char * white_spaces = ",\n \t";
            strArr data = split(word,white_spaces);
            char ** clean_num = data.arr;
            char * num = NULL;
            num = clean_num[0];
            for (c=1;c<data.len; c++)
                if (!isdigit(num[c]) && strchr( ",.-+", num[c] )==NULL)
                    Immediate_number=false;
            error_num = 1;
            if(Immediate_number==false)
                is_error=true;
            free(data.arr);
        }
        int directive_type = keyWordsValid(word)+6;
        switch (directive_type)
        {
            case 0:
                is_data = true;
                type_line = data;
                break;
            case 1:
                is_string = true;
                type_line = string;
                sum_operands=1;
                break;
            default:
                if(directive_type>1 && directive_type<4)
                {
                    type_line=extORent;
                    is_command = true;
                    sum_operands=1;
                }
                break;
        }
        /* check if there is invalid char's */
        int invalidChars_type = 
            (type_line!=data && type_line!=string && type_line!=extORent) ? invalidChars(word) : -1;
            
        /**
         * if the prev word is command check 
         * the commas based sum opernds for the command 
         */
        type = keyWordsValid(word);
        if(type>=0) 
        {
            is_command = true;
            type_line = cmd;
            sum_operands = type;
        }
        /* check if there is invalid chars */
        if(
            (is_symbol && invalidChars_type>1) ||
            (is_command && invalidChars_type>0) ||
            ((is_data || is_string) && (invalidChars_type>2 || invalidChars_type==1))
        )
        {
            error_num = 1;
            is_error = true;
        }
        /** the logic condition for each boolean below is:
         * 1. ensure which type of command line is.
         * 2. ensure to get the operands (and not the command imself)
         * 
         * the type_line describe the type of this line 
         * the is_<some> describe the current word
         */
        bool dataNum = (type_line==data && is_data==false);
        bool dataString = (type_line==string && is_string==false);
        bool directive_extORent = (type_line==extORent && is_extORent==false);
        bool cmdOperands = (
            (type==-1 || type==-2) &&
            type_line!=sym &&
            is_symbol==false &&
            is_string==false &&
            is_data==false && 
            is_extORent==false
        );
        if(!directive_extORent && ((dataNum || dataString) || (cmdOperands && type < 14)))
        {
            if(word[strlen(word)-1]=='\"') e_quotes = true;
            /** ensure it hase a (") char 
             * at the begining of the text
             * and remove it
             */
            if(dataString && is_data_stored==false) word+=1;
            int j = 0;
            char * delimeter = "\",\t \n";
            strArr w = split(word,delimeter);
            char ** dataArr = w.arr;
            int len = w.len;
            while (j<len)
            {
                /** if there is space in string
                 * it will splited by the main validation
                 * and need to be an one string 
                 * here it concatenate the parts
                 * 1. ensure it second part
                 * 2. add space between parts */
                if(is_data_stored && dataString)
                {
                    char *space = " ";
                    char *q=scat(operands[operands_index-1],space);
                    operands[operands_index-1] = q;
                    q=scat(operands[operands_index-1],dataArr[j]);
                    operands[operands_index-1] = q;
                }
                else
                {
                    if(dataArr[j][strlen(dataArr[j])-1]!=':'){
                        operands[operands_index] = dataArr[j];
                        operands_index++;
                    }
                }
                j++;
                is_data_stored=true;
                if(type_line == data) sum_operands=operands_index;
            }
            free(w.arr);
        }
        /* save the words for the struct */
        if((is_symbol || directive_extORent) && is_error==false) symbol = word;
        if((is_command) && is_error==false) command = word;

        if(is_error)
        {
            if(error_num>4) fprintf( stderr,"Error at line %d: %s\n",line_num ,error[error_num]);
            else fprintf( stderr,"Error at line %d: The word \"%s\" %s.\n",line_num ,word,error[error_num]);
            error_printed = error_num;
        }
        prev_word=word;
        i++;
        if(is_error) line_error = true;
    }
    /* Errors by line or more then noe word level */
    if(skip==false && i>0)
    {
        if(operands_index>sum_operands && type_line==cmd){
            line_error = true;
            error_num = 11;
        }
        else if(
            (type_line!=extORent && type_line!=string)
            && operands_index<sum_operands){
            line_error = true;
            error_num = 12;
        }
        else if(
            (type_line!=string && type_line!=extORent) 
            && (sum_commas>0) && sum_commas>=operands_index){
            line_error=true;
            error_num = 5;
        }
        else if(sum_commas<operands_index-1){
            line_error=true;
            error_num = 13;
        }
        if(type_line==err) 
        {
            line_error=true;
            error_num = 9;
        }
        else if(is_data_stored==false && sum_operands>1)
        {
            line_error=true;
            error_num = 10;
        }
        else if(type_line==string){
            if(s_quotes && e_quotes==false){
                line_error=true;
                error_num = 8;
            }
            else if(s_quotes==false && e_quotes){
                line_error=true;
                error_num = 7;
            }
            else if(s_quotes==false && e_quotes==false){
                error_num = 6;
                line_error=true;
            }
        }
        else if(type_line==extORent){
            sum_operands=0;
        }
        if(line_error && error_printed!=error_num)
            fprintf( stderr,"Error at line %d: %s\n",line_num ,error[error_num]);

        is_command_struct = (type_line==cmd);
        is_string_struct = (type_line==string);
        if(line_error == false)
        {
            store_as_struct(
				symbol,
				command,
				sum_operands,
				is_command_struct,
				is_string_struct,
				operands
            );
        }
    }
    free(words);
    return (skip==false && line_error==true) ? false : true;
}
