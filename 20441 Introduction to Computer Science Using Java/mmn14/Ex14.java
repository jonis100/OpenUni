
/**
 * Answers for mmn 14 
 * @author (Yoni Shieber)
 * @version (a2020)
 */
public  class  Ex14
{
    /**
     * Count substring started, ended and contain one time a given char (c) on a given string (s)
     * Space complication o(1). Time complication o(n).
     * @param s string c char
     * @return int
     */
    public static int subStrC(String s, char c)
    {
        int counter = 0;
        for (int i =0; i<s.length(); i++)
        {
            if (s.charAt(i) == c)
                counter ++;
        }
        if (counter<3)
            return 0;
        return counter-2;
    }

    /**
     * Count substring started, ended and contain a given char (c) maximum given k times a on a given string (s)
     * Space complication o(1). Time complication o(n).
     * @param s string c char k int
     * @return int
     */
    public static int subStrMaxC(String s, char c, int k) 
    {
        int counter = 0;
        for (int i =0; i<s.length(); i++)
            if (s.charAt(i) == c)
                counter ++;
        if (counter < 2)
            return 0;
        else if (k > counter-2)
            k = counter-2;
        else if (k == 0)
            return counter-1;
        else if (k == 1)
            return counter-1 + counter-2;
        int res = 0;
        for (int i = 1; i <= k+2; i++)
            res += counter-i;
        return res;    
    }

    /**
     * This method get array 0f 0 1 values and change digit 1 to minimal distance from 0 digit nearby
     * Space complication o(1). Time complication o(n).
     * @param a array
     * @return void
     */
    public static void zeroDistance (int [] a) 
    {
        int counter = 0;
        int zeroCounter1 = 0;
        for (int i = 0;i < a.length;i++){
            if (a[i] == 0){
                counter = 0;
                zeroCounter1++;}
            else 
            if (zeroCounter1 != 0){
                counter ++;
                a[i] = counter;
            }
        }
        counter = 0;
        int zeroCounter2 = 0;
        for (int i = a.length-1;i >= 0;i--){
            if (a[i] == 0){
                counter = 0;
                zeroCounter2++;
            }
            else 
            if (zeroCounter2 != 0){
                counter ++;
                if (counter < a[i] || zeroCounter2 == zeroCounter1)
                    a[i] = counter;
            }
        }
    }

    /**
     * this method get string s and string t and check if t is transformation of s
     * @param String s String t
     * @return boolean 
     */
    public static boolean isTrans (String s, String t)
    {
        if (s.length() > t.length() || s.length() == 0 && 0 < t.length())
            return false;
        if (s.length() == 0)
            return true;
        if (s.charAt(0) == t.charAt(0))
            return isTrans(s.substring(1) , t.substring(1)) || isTrans(s, t.substring(1));
        return false;    
    }

    /**
     * get array and return how many paths out by adding value of digits in cell array to row or colum  
     * @param int[][] mat
     * @return int
     */
    public static int countPaths(int [][] mat)
    {
        return countPaths(mat, 0, 0);   
    }

    private static int countPaths(int [][] mat, int i, int j)
    {
        if (i<0 || i>=mat.length || j<0 || j>=mat[0].length)
            return 0;
        
        if (i == mat.length-1 && j == mat[0].length-1)
            return 1;
        if (mat [i][j] == 0)
            return 0;    
        if ( mat[i][j]/10 == mat[i][j]%10)
            return countPaths(mat, i+mat[i][j]/10 , j + mat[i][j]%10);
        return  countPaths(mat, i+mat[i][j]/10 , j + mat[i][j]%10) + countPaths(mat, i+mat[i][j]%10 , j + mat[i][j]/10);
    }
}

