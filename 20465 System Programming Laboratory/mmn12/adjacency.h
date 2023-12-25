# include <stdio.h>

# define TRUE 1
# define FALSE 0
# define N 11
typedef int adjmat [N][N];
void scanMat(adjmat myMat);
void printMat(adjmat myMat);
int path(adjmat myMat, int u , int v);
