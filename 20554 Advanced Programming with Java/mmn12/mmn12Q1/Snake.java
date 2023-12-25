
package mmn12Q1;

public class Snake extends Reptiles{
	
	public Snake(String name, int age, String color) {
		super(name, age, color, false); //Snake never has legs
	}
	//equals @override
	public boolean equals(Object anotherAnimal){
		if(anotherAnimal instanceof Snake && 
				super.equals(anotherAnimal)) {
				return true;
		}
		return false;
	}
	//clone @override
	public Snake clone(){
		Snake newSnake = new Snake(this._name, this._age, this._color);
		return newSnake;
	}
	public void eat(){
		System.out.println("Ssss, i'm eating as snake, Yaamy!");
	}
	public void sleep(){
		System.out.println("Ssss, i'm sleeping as snake! Zzzzzzz....");
	}
	public void crawl() {
		System.out.println("Sssss, I'm crawling as sanke, And may bit. Be carful!!");
	}
}
