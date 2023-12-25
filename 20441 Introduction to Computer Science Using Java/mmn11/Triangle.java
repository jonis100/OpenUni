
/**
 This program get from users the length of three triangle's Sides, calculate
 and print perimeter and area.
 * @author (Yoni Shieber. ID *********)
 * @version (20\11\2019)
 */
import java.util.Scanner;
public class Triangle
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculate tha perimeter and the area of "
        +"a given triangle");
        System.out.println ();
        System.out.println ("Please enter the three lengths of the triangle's sides");
        
        double perimeter, s, area;
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        //checkig the correct parameters
        if (a<=0 || b<=0 || c<=0 )
           System.out.println ("One of Number you insert is invalid"); 
        else   if (a+b <= c || b+c <= a || a+c <= b)
           System.out.println ("This sides not provied triangle");
                else //Parameters is right
                {
                    perimeter = a + b + c;
                    s = perimeter/2.0; //calculate half perimeter
                    area = Math.sqrt(s*(s-a)*(s-b)*(s-c)); //calculate the area 
                    System.out.println ("Perimeter is\b" + perimeter );
                    System.out.println ("Area is " + area );
                }
    } // end of method main
} // end of triangle class
