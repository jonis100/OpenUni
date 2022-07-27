package mmn12Q1;

public class Pigeon extends Birds{
	
	public Pigeon(String name, int age, String color, boolean canFly, double wingspan) {
		super(name, age, color, canFly, wingspan);
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Pigeon && 
				super.equals(anotherAnimal) &&
				this._wingspan == ((Pigeon)anotherAnimal)._wingspan) {
				return true;
		}
		return false;
	}
	//clone @override
	public Pigeon clone(){
		Pigeon newPigeon = new Pigeon(this._name, this._age, this._color, this._canFly, this._wingspan);
		return newPigeon;
	}
	public void eat(){
		System.out.println("Hi, i'm eating as Pigeon, Yaamy!");
	}
	public void sleep(){
		System.out.println("Hi, i'm sleeping as Pigeon, Zzzzzzz!");
	}



}
