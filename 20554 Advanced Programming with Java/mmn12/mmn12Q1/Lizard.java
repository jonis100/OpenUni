package mmn12Q1;

public class Lizard extends Reptiles{
	public Lizard(String name, int age, String color) {
		super(name, age, color, true);
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Lizard && 
				super.equals(anotherAnimal)) {
				return true;
		}
		return false;
	}
	//clone @override
	public Lizard clone(){
		Lizard newLizard = new Lizard(this._name, this._age, this._color);
		return newLizard;
	}
	public void eat(){
		System.out.println("Ssss, i'm eating as lizard, Yaamy!");
	}
	public void sleep(){
		System.out.println("Ssss, i'm sleeping as lizard! Zzzzzzz....");
	}
	public void crawl() {
		System.out.println("Sssss, I'm crawling as lizard, And can't bit. No needed carful");
	}

}
