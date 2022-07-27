package mmn12Q1;

public abstract class Animals {

	protected String _name;
	protected int _age;
	protected String _color;
	
	public Animals(String name, int age, String color) {
		this._name = name;
		this._age = age;
		this._color = color;
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
	
	//getters
	public String getName() {
		return _name;
	}
	public int getAge() {
		return _age;
	}
	public String getColor() {
		return _color;
	}
	public String toString() {
		return "Name: " + this._name + "\n" +
				"Age: " + this._age + "\n" +
				"Color: " + this._color + "\n";
	}
	public boolean equals(Object anothrObject) {
		if (anothrObject instanceof Animals) {
			if (this._name == ((Animals) anothrObject).getName() &&
				this._age == ((Animals) anothrObject).getAge() &&
				this._color == ((Animals) anothrObject).getColor() )
				return true;
			}				
		return false;
	}
	public void flyIfYouCan(){
		if (this instanceof Birds) {
			((Birds)this).fly();
		}
	}
	public void barkIfYouCan(){
		if (this instanceof Dog) {
			((Dog)this).bark();
		}
	}
	public void crawlIfYouCan(){
			if (this instanceof Reptiles) {
				((Reptiles)this).crawl();
			}
	}
	public abstract void eat();
	public abstract void sleep();
	
}
