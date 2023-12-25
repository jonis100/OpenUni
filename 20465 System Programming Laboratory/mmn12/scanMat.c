# include "adjacency.h"
/*get matrix from user and save it as adjmat */
int i, j;
void scanMat(adjmat myMat)
{
	for (i = 0; i<N; i++)
	{
		for (j = 0; j<N; j++)
		{
			scanf ("%d",&myMat[i][j]);
		}
	}
}
