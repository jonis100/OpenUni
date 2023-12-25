package mmn12Q1;

public class Ostrich extends Birds {

	protected Owner _owner;

	public Ostrich(String name, int age, String color, Owner owner, float wingspan) {
		this._name = name;
		this._age = age;
		this._color = color;
		this._owner = owner;
		this._CanFly = false;
		this._Wingspan = wingspan;

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
  public void setCanFly(boolean canFly){
    this._CanFly = canFly;
  }
  public void setOwner(float wingspan){
    this._Wingspan = wingspan;
  }

	public void toString() {
		System.out.println("Ostrich name: " + this._name);
		System.out.println("Age: " + this._age);
		System.out.println("Color: " + this._color);
		System.out.println("Owner: " + this._owner.toString());
		System.out.println ("canFly: " this._CanFly);
		System.out.println ("wingspan size:" this._Wingspan);
	}
  public void getName() {
  }
  public void getNAge() {
  }
  public void getColor() {
  }
	public void eat();{
    System.out.println("Hi, i'm eating as Ostrich, Yaamy!")
	}
	public void sleep();{
    System.out.println("Hi, i'm sleeping as Ostrich, Zzzzzzz!")
	}


}
