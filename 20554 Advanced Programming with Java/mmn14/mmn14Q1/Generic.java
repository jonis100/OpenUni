package mmn14Q1;

public class Generic {
	
	public  <E extends Comparable<E>> E returnMin(Set<E> set) {
		E min = set.get(0);
		for (int i=1; i<set.size(); i++) {
			if (set.get(i).compareTo(min)<0)
				min = set.get(i);
		}
		return min;
	}

}
