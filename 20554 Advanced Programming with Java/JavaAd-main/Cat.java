package mmn12Q1;

public class Cat extends Mammals {

	protected Owner _owner;

	public Cat(String name, int age, String color, Owner owner) {
		this._name = name;
		this._age = age;
		this._color = color;
		this._owner = owner;
		this._mammalType = PLACNTAL;

	}
	//setters
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
	//getters
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
	//toString @override
	public void toString() {
		System.out.println("Name: " + this._name + "\n" +
				"Age: " + this._age + "\n" +
				"Color: " + this._color + "\n" +
				"Owner: " + this._owner.toString() + "\n" +
				"MammalType: " + this._mammalType);
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(this instanceof Cat &&
				this._name == anotherCat.getName &&
				this._age == anotherCat.getAge &&
				this._color == anotherCat.getColor &&
				this._owner == anotherCat.getOwner &&
				)
			return true;
		return false;
	}
	public Cat clone(){
		String name = this._name;
		int age = this._ago;
		String color = this._color;
		Owner owner = this._owner;
		Cat newCat = new cat(name, age, color, owner);
		return newCat;
	}
	public void eat();{
		System.out.println("Meow, i'm eating as cat, Yaamy!")
	}
	public void sleep();{
		System.out.println("Meow, i'm sleeping as cat! Zzzzzzz....")
	}

	public void howl() {
		System.out.println("Meow Meow, The cat is howls");
	}

}
