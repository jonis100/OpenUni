/**
 This program get from users the coordinates of two triangle, calculate each 
 side and check if those congruent and print the results.
 * @author (Yoni Shieber. ID *********)
 * @version (21\11\2019)
 */
import java.util.Scanner;
public class Congruent
{   
   public static void main (String [] args)
   { 
    Scanner scan = new Scanner (System.in);
    System.out.println("This program get coordinates of two triangle, print the "+ 
     "sides of each and calculate congruent");
    System.out.println();
    
    //coordinates of Traingle 1 
    System.out.println("Please insert the coordinates of the first triangle");
    double x11 = scan.nextDouble();    //coordinate 1
    double y11 = scan.nextDouble();  
    double x12 = scan.nextDouble();    //coordinate 2
    double y12 = scan.nextDouble();;
    double x13 = scan.nextDouble();    //coordinate 3
    double y13 = scan.nextDouble();
    
    //coordinates of Traingle 2
    System.out.println("Please insert the coordinates of the second triangle");
    double x21 = scan.nextDouble();    //coordinate 1
    double y21 = scan.nextDouble();  
    double x22 = scan.nextDouble();    //coordinate 2
    double y22 = scan.nextDouble();
    double x23 = scan.nextDouble();    //coordinate 3
    double y23 = scan.nextDouble();
    
    //calculate the sides of triangle 1
    double side11 = Math.sqrt(Math.pow((x11-x13),2)+Math.pow((y11-y13),2));
    double side12 = Math.sqrt(Math.pow((x12-x11),2)+Math.pow((y12-y11),2));
    double side13 = Math.sqrt(Math.pow((x13-x12),2)+Math.pow((y13-y12),2));
    
    //calculate the sides of triangle 2
    double side21 = Math.sqrt(Math.pow((x21-x23),2)+Math.pow((y21-y23),2));
    double side22 = Math.sqrt(Math.pow((x22-x11),2)+Math.pow((y22-y21),2));
    double side23 = Math.sqrt(Math.pow((x23-x22),2)+Math.pow((y23-y22),2));
    
    System.out.println("The first triangle is ("+ x11 +", "+ y11 +") ("+ x12 +", "+ y12 +")." +
    " ("+ x13 +", "+ y13 +")");
    System.out.println("Its lengths are " +  side11 +", " + side12 +", " + side13 +".");
    System.out.println();
    System.out.println("The second triangle is ("+ x21+", "+ y21 +") ("+ x22+", "+ y22 +")." +
    " ("+ x23 +", "+ y23 +")");
    System.out.println("Its lengths are " + side21 +", " + side22 +", " + side23 +".");
    System.out.println();
    
    //check the Congruent
    if ((side11 == side21 && side12 == side22 && side13 == side23) || 
        (side11 == side22 && side12 == side21 && side13 == side23) ||
        (side11 == side22 && side12 == side23 && side13 == side21) ||
        (side11 == side21 && side12 == side23 && side13 == side22) ||
        (side11 == side23 && side12 == side21 && side13 == side22) ||
        (side11 == side23 && side12 == side22 && side13 == side21))
    {
        System.out.println("The triangles are congruent");
    }
    else
    {
        System.out.println("The triangles are not congruent");
    } //end of check the Congruent
   } // end of method main
} // end of congruent class
