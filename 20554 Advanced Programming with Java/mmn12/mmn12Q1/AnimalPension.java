package mmn12Q1;
import java.util.ArrayList;

public  class AnimalPension {
	int pensionLength = 10;
	ArrayList<Animals> pension = new ArrayList<Animals>(pensionLength);

	public void scan(){
		for (int i = 0; i < pension.size(); i++) {
			Animals tempAnimal = pension.get(i);
			tempAnimal.eat();
			tempAnimal.sleep();
			tempAnimal.barkIfYouCan();
			tempAnimal.flyIfYouCan();
			tempAnimal.crawlIfYouCan();
		}
			
	}
	
	public void init(){
		Owner Ran = new Owner("Ran", 123456789);
		Owner Rina = new Owner("Rina", 987654321);
		Animals dog = new Dog("lucky", 6, "Brown" , Ran);
		Animals cat = new Cat("Glida", 3, "Black and White", Rina);
		Animals snake = new Snake("Pethy", 6, "Yellow rings");
		Animals lizard =  new Lizard("fredy", 1, "Black matte");
		Animals ostrich =  new Ostrich("yaeni", 2, "Light brown", 1.7);
		Animals pigeon = new Pigeon("Pigi", 5, "Gray", true, 0.5);
		this.pension.add(dog);
		this.pension.add(lizard);
		this.pension.add(cat);
		this.pension.add(snake);
		this.pension.add(ostrich);
		this.pension.add(pigeon);
	}

	public void gSection(){
		System.out.println("Here in g section");
		Dog myDog1 = (Dog)this.pension.get(0);
		Dog myDog2 = myDog1.clone();
		System.out.println("Before changing owner: " + "\n" +
							myDog1.toString() + "\n" +
							myDog2.toString());
		System.out.println("Equals: " + myDog1.equals(myDog2));
		Owner Gila = new Owner("Gila", 523146987);
		myDog2.setOwner(Gila);
		System.out.println("After changing owner: " + "\n" +
				myDog1.toString() + "\n" +
				myDog2.toString());
		System.out.println("Equals: " + myDog1.equals(myDog2));
	}
	public void compare() {
		
	}
}
