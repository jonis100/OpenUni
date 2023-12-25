#include <stdio.h>

/* This function get string and print if it is realy sequence (no reapits) up/down or sequence (contain reapits) up/down */

void f_sequence(char str[])
{
	int i;
	int counter_same_char = 1;
	int up_sequence = 1;
	int up_sequence_contain_reapets = 1;
	int down_sequence = 1;
	int down_sequence_contain_reapets = 1;
	printf("\n This is the string\n");
	puts(str);

	for (i = 0; str[i+1] !='\0'; i++)
	{
		if (str[i] > str[i+1]) {
			up_sequence = 0;   
			up_sequence_contain_reapets = 0;
			}
		if (str[i] < str[i+1]){
			down_sequence = 0;   
			down_sequence_contain_reapets = 0;
			}
		if (str[i] == str[i+1])	{
			up_sequence = 0;
			down_sequence = 0; 
			counter_same_char += 1;  
			}
		
	}
	if (counter_same_char == strlen(str)){

		 down_sequence_contain_reapets = 0;
	}
	if (down_sequence){
		printf("\nThis is a down sequence and no reapets\n");
	}
	else if (down_sequence_contain_reapets){
		printf("\nThis is a down sequence contain reapets\n");
	}	
	if (up_sequence){
		printf("\nThis is a up sequence and no reapets\n");
	}
	else if (up_sequence_contain_reapets){
		printf("\nThis is a up sequence contain reapets\n");
	}
	if ( down_sequence == 0 &&  down_sequence_contain_reapets == 0 &&
		up_sequence == 0 && up_sequence_contain_reapets == 0){
		printf("\nThis string is not a consisted sequence\n");
	}

}
void main()
{	
	char str[100];
	printf("\n Please insert a string\n");
	gets(str);
	f_sequence(str);
}

