package mmn14Q1;

public class Person implements Comparable<Person> {
	
	int _ID;
	String _firstName;
	String _lastName;
	int _bornYear;
	
	public Person (int id, String firstName, String _lastName, int bornYear) {
		this._ID = id;
		this._firstName = firstName;
		this._lastName = _lastName;
		this._bornYear = bornYear;
	}
	
	@Override
	public int compareTo(Person person) {
		return this._bornYear-person._bornYear;
		
	}
	
	@Override
	public String toString() {
		return this._ID + " " + this._firstName + " " + this._lastName + " " + this._bornYear;
	}
	

}
