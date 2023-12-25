package mmn12Q1;

public class Ostrich extends Birds {


	public Ostrich(String name, int age, String color, double wingspan) {
		super(name, age, color, false, wingspan); //Ostrich never can fly
	}

	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Ostrich && 
				super.equals(anotherAnimal) &&
				this._wingspan == ((Ostrich)anotherAnimal)._wingspan) {
				return true;
		}
		return false;
	}
	//clone @override
	public Ostrich clone(){
		Ostrich newOstrich = new Ostrich(this._name, this._age, this._color, this._wingspan);
		return newOstrich;
	}
	public boolean getCanFly(boolean canFly){
		return this._canFly;
	}
	public void eat(){
		System.out.println("Hi, i'm eating as Ostrich, Yaamy!");
	}
	public void sleep(){
		System.out.println("Hi, i'm sleeping as Ostrich, Zzzzzzz!");
	}


}
