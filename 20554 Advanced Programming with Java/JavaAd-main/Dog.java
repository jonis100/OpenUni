package mmn12Q1;

public class Dog extends Mammals {

	protected Owner _owner;

	public Dog(String name, int age, String color, Owner owner) {
		this._name = name;
		this._age = age;
		this._color = color;
		this._owner = owner;
		this._mammalType = PLACNTAL;

	}

	public void setName(String name) {
		this._name = name;
	}
	public void setAge(int age) {
		if (age > 0)
			this._age = age;
	}
	public void setColor(String color){
		this._color = color;
	}
	public void setOwner(Owner owner){
		this._owner = owner;
	}
	public void getName(String name) {
		System.out.println (this._name);
	}
	public void getAge(int age) {
		System.out.println (this._age);
	}
	public void getColor(String color){
		System.out.println (this._color);
	}
	public void getOwner(Owner owner){
		System.out.println (this._owner.toString());
	}



	public void toString() {
		System.out.println("Dog name: " + this._name);
		System.out.println("Age: " + this._age);
		System.out.println("Color: " + this._color);
		System.out.println("Owner: " + this._owner.toString());
		System.out.println("MammalType: " + this._mammalType);
	}
	public void eat();{
		System.out.println("Woof, i'm eating as dog, Yaamy!")
	}
	public void sleep();{
			System.out.println("Woof, i'm sleeping as dog! Zzzzzzz....")
	}

	public void bark() {
	System.out.println("Woof Woof. The Dog barking");
	}

}
