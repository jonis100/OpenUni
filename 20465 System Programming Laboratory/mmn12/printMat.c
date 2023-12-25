# include "adjacency.h"
/*get adjmat matrix and print it on the screen */
int i, j;
void printMat(adjmat myMat)
{
	printf("\nyour mat is:\n");
	for (i = 0; i<=N; i++)
	{
		for (j = 0; j<=N; j++)
		{
			if (i == 0 && j == 0)
			{
				printf("  ");
			}
			else if (i == 0)
			{
				printf(" %d",j-1);
			}
			else if (j == 0)
			{
				printf(" %d",i-1);
			}
			else 
			{
				printf(" %d",myMat[i-1][j-1]);
			}
			if (j == N)
			{
				printf("\n");
			}
		}
		
	}
}
