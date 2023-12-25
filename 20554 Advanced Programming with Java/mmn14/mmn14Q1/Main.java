package mmn14Q1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Initialize Set array in value size
		int value = 3;
		Set<Integer>[] setArr = new Set[value];
		for (int i= 0; i<value; i++){
			setArr[i] = util.initializeSet();
			System.out.println("Set"+(i+1)+": " +setArr[i].toString());
			
		}
		//B section
		
		//union set0 and set1
		setArr[0].union(setArr[1]);
		System.out.println("Union set1 + set2: " + setArr[0].toString());
		
		//intersect s0 and s2
		setArr[0].intersect(setArr[2]);
		System.out.println("Intersect set1 + set3: " + setArr[0].toString());
		
	    Scanner sc = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter Two Integers in rang 0-100");
	    Integer[] numbers = new Integer[2];
        for(int i = 0; i < numbers.length; ++i) {
            numbers[i] = sc.nextInt();
        }
        System.out.println("subset1: "+ setArr[0].toString());
        System.out.println("subset2: "+ setArr[1].toString());
        System.out.println("subset3: "+ setArr[2].toString());
        
        Set<Integer> set3 = new Set<Integer>(numbers);
        System.out.println("Set 4 is: " + set3.toString());
        for (int i=0; i<value; i++) {
        	 if (setArr[i].isSubset(set3))
        			 System.out.println("Set 4 is a subset of: set"+(i+1));
        	 else 
        		 System.out.println("Set 4 is NOT a subset of: set"+(i+1));
        	
         }
        
        
        System.out.println("Enter one Integer in rang 0-100 as member");
        int member = sc.nextInt();
        boolean resExist = (setArr[0].isMember(member) == -1) ? false : true;
        if (resExist) {
        	System.out.println("Member is exist in set1");
        }
        else
        	System.out.println("Member is does not exist in set1");
        setArr[1].insert(member);
        System.out.println("set2 after adding the input member: " + setArr[1].toString());
        System.out.println("set3 BEFORE removing the input member: " +setArr[2].toString());
        setArr[2].delete(member);
        System.out.println("set3 after removing the input member: " +setArr[2].toString());       
        sc.close();
        
        //G section
        
        Person personArr[] = new Person[5];
        personArr[0] = new Person(123456789, "Israel", "Israeli", 2000);
        personArr[1] = new Person(987654321, "Moshe", "Shalom", 2013);
        personArr[2] = new Person(987321654, "Lin", "Rosen", 2010);
        personArr[3] = new Person(123654987, "Jeff", "Riga", 2010);
        personArr[4] = new Person(456321789, "Rona", "Sason", 2005);
        
        Set<Person> personSet = new Set<Person>(personArr);
        //printing all persons
        for (int i=0; i<5; i++) {
        	System.out.println(personSet.get(i).toString());
        }
        Generic gen = new Generic();
        System.out.println("The minimal member is: " + gen.returnMin(personSet));
	}
	
}
