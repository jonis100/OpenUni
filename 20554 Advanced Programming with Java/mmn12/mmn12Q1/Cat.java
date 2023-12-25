package mmn12Q1;

public class Cat extends Mammals {

	public Cat(String name, int age, String color, Owner owner) {
		super(name, age, color, owner);
	}

	//toString @override
	public String toString() {
		return "Hi, I am Cat " + "\n" +
				"My owner: " + this.getOwner() + "\n"+
				super.toString();
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Cat && super.equals(anotherAnimal)) {
				return true;
		}
		return false;
	}
	//clone @override
	public Cat clone(){
		Cat newCat = new Cat(this._name, this._age, this._color, this._owner);
		return newCat;
	}
	public void eat(){
		System.out.println("Meow, i'm eating as cat, Yaamy!");
	}
	public void sleep(){
		System.out.println("Meow, i'm sleeping as cat! Zzzzzzz....");
	}

	public void howl() {
		System.out.println("Meow Meow, The cat is howls");
	}

}
