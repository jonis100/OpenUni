# include "adjacency.h"
/*calculate if there is have correct way from u to v in M matrix (type adjmat)   */

int j, i;
int path(adjmat A, int u, int v)
{
	if (u>N || v>N)
	{
		return FALSE;
	}	
	if (u<N && u == v)
	{
		return TRUE;
	}
	j = v;
	for (i = 0; i < N; i++)
	{
		if (A[i][j]) 
		{
			j=i;
			if (i == u)
			{
				return TRUE;
			}
		}
	}
	return FALSE;
}
