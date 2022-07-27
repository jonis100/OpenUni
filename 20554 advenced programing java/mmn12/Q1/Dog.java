package mmn12Q1;

public class Dog extends Mammals {



	public Dog(String name, int age, String color, Owner owner) {
		super(name, age, color, owner);

	}

	//toString @override
	public String toString() {
		return "Hi, I am Dog " + "\n" +
				"My owner: " + this.getOwner() + "\n"+
				super.toString();
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Dog && super.equals(anotherAnimal)) {
				return true;
		}
		return false;
	}
	//clone @override
	public Dog clone(){
		Dog newDog = new Dog(this._name, this._age, this._color, this._owner);
		return newDog;
	}
	public void eat(){
		System.out.println("Woof, i'm eating as dog, Yaamy!");
	}
	public void sleep(){
		System.out.println("Woof, i'm sleeping as dog! Zzzzzzz....");
	}

	public void bark() {
		System.out.println("Woof Woof. The Dog barking");
	}

}
