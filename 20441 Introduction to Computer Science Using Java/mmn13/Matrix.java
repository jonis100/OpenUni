/**
 * represent image by two-dimensional array
 *
 * @author (YONI SHIEBER)
 * @version (a2020)
 */
public class Matrix
{
    final int MAX_PIXEL_SIZE = 255;
    private int[][] _matrix;
    /**
    * Constructor for objects of class Matrix
    * Constructs a Matrix from two-dimensional as well as values of this Matrix will
    * be the same as the dimensional and values of two-dimensional arrey  
     */    
    public Matrix (int [][] array)   
    {
       _matrix = new int [array.length] [array[0].length]; 
       for (int i = 0; i < _matrix.length; i++)
        for (int j = 0; j < _matrix[i].length; j++)
            _matrix[i][j] = array[i][j];
    }
    /**
     * Constructor for objects of class Matrix 
     * Constructs a size1 by size2 Matrix of zeroes
     */    
    public Matrix (int size1, int size2)
    {
        _matrix = new int [size1] [size2];
    }  
    private boolean isValid (int row,int col)
    {
        return ((row>-1 && row<_matrix.length) && (col>-1 && col<_matrix[0].length));
    }
    /**
     * return string represent the matrix
     * @return String of matrix
     */
    public String toString()
    {
     String matrixString = "";   
       for (int i = 0; i < _matrix.length; i++)
     {
        for (int j = 0; j < _matrix[i].length-1; j++) 
        {
            matrixString += (_matrix[i][j] + "\t");
        } 
        matrixString += (_matrix[i][_matrix[i].length-1]);
        matrixString += ("\n");
     }
     return matrixString;
    }
    /**
     * get matrix and return new negative matrix
     * @return new negativ Matrix
     */
    public Matrix makeNegative() 
    {
     Matrix matrixNegative = new Matrix (_matrix);
         for (int i = 0; i < matrixNegative._matrix.length; i++)
     {
        for (int j = 0; j < matrixNegative._matrix[i].length; j++)
            matrixNegative._matrix [i][j] = MAX_PIXEL_SIZE -  matrixNegative._matrix [i][j];
        }   
     return matrixNegative;   
    }
    /**
     * get matrix represent photo and return fixed one
     * @return new Matrix
     */
    public Matrix imageFilterAverage()
    {
     Matrix filteredMatrix = new Matrix(_matrix);
        for (int i = 0;i<_matrix.length;i++)
        {
            for(int j = 0;j<_matrix[i].length;j++)
        {
            int averege = 0;
            int counterCell = 0;
            averege += _matrix[i][j];
            counterCell++;
            if (isValid(i-1,j-1))
            {
                averege += _matrix[i-1][j-1];
                counterCell++;
            }   
            if (isValid(i-1,j))
            {
                averege += _matrix[i-1][j];
                counterCell++;
            }   
            if (isValid(i-1,j+1))
            {
                averege += _matrix[i-1][j+1];
                counterCell++;
            }   
            if (isValid(i,j-1))
            {
                averege += _matrix[i][j-1];
                counterCell++;
            }   
            if (isValid(i,j+1))
            {
                averege += _matrix[i][j+1];
                counterCell++;
            }   
            if (isValid(i+1,j-1))
            {
                averege += _matrix[i+1][j-1];
                counterCell++;
            }   
            if (isValid(i+1,j))
            {
                averege += _matrix[i+1][j];
                counterCell++;
            }   
            if (isValid(i+1,j+1))
            {
                averege += _matrix[i+1][j+1];
                counterCell++;
            }   
            filteredMatrix._matrix[i][j] = averege/counterCell;
        }
      }
        return filteredMatrix;
    }
    /**
     * get matrix represent photo and rotate 90 dagrees with clockwise
     * @return new rotate Matrix
     */
     public Matrix rotateClockwise()
     {
        Matrix rotatedMatrix = new Matrix( _matrix[0].length, _matrix.length);
        for (int i = 0; i<_matrix.length; i++)
            for (int j = 0; j<_matrix[i].length; j++)
                rotatedMatrix._matrix[j][_matrix.length-1-i] = _matrix[i][j];
        return rotatedMatrix;        
      }
    /**
     * get matrix represent photo and rotate 90 dagrees count clockwise
     * @return new Matrix
     */ 
     public Matrix rotateCounterClockwise() 
     {
        Matrix CounterRotatedMatrix = new Matrix(_matrix[0].length, _matrix.length);
        for (int i = 0; i<_matrix.length; i++)
            for (int j = 0; j<_matrix[i].length; j++)
                CounterRotatedMatrix._matrix[_matrix[i].length-1-j][i] = _matrix[i][j];
        return CounterRotatedMatrix;
        }
}