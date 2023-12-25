package mmn12Q2;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello , Please insert 4 integers in one line. Spaces between only \n"+
							"In ordre to represent 2 rational numbers. For example: 1 2 3 4 = 1/2 3/4");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();     
		String[] numbers = input.split(" ");
		try {
			int P1 = Integer.parseInt((String)(Array.get(numbers, 0)));
			int Q1 = Integer.parseInt((String)(Array.get(numbers, 1)));
			int P2 = Integer.parseInt((String)(Array.get(numbers, 2)));
			int Q2 = Integer.parseInt((String)(Array.get(numbers, 3)));
			Rational r1 = new Rational(P1, Q1);
			Rational r2 = new Rational(P2, Q2);
			Rational res = r1.multiply(r2);
			res = res.reduce();
			System.out.println( r1.toString() + " * " + r2.toString() + " = " + res.toString());
			scanner.close();
		}
		catch (NumberFormatException e){
			throw new IllegalArgumentException("Please insert posotive integer, Try anothr arguments");
		}
		return;
	}

}
