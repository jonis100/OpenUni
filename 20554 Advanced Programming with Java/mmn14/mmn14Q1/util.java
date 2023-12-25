package mmn14Q1;

public class util {

	public static Set<Integer> initializeSet() {
		int values = 10;
		Set<Integer> newSet = new Set<Integer>();
		while (newSet.size()<values) {
			//randomize number
			int temp = (int)(Math.random()*100);
			//insert to Set
			newSet.insert(temp);
		}
		return newSet;
	}
	
}
