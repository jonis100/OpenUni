# include "adjacency.h"

/* This program get by user matrix (size N*N, N define in Header file) represent tree (i represent father, j represent son)
and get tow indexes u, v and calculate possible way from u to v and return results
*/
int main()
{
	int u, v;
	adjmat myMat;
	printf("\nplease insert %d times 0 or 1 for the first row and do so for all rowes until th%d row\n", N, N );
	scanMat(myMat);
	printMat(myMat);
	printf("\nplease insert another 2 indexes in order to check possible path in the mat\n");
	while ( scanf("%d %d", &u, &v)!= EOF && ( u != -1 && v != -1))
	{
		if (path(myMat, u, v))
		{
			printf("\nYes. Exsist correct path from %d to %d", u, v);
		}
		else
		{
			printf("\nNo correct path from %d to %d", u, v);
		}	
		printf("\nplease insert another 2 indexes in order to check possible path in the mat");
	} 
	return 0;
}
