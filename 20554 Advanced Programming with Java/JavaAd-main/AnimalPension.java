package mmn12Q1;
import java.util.ArrayList;


public  class AnimalPension {
	int pensionLength = 5;
	ArrayList<Animals> pension = new ArrayList<Animals>(n);

	public void toString(){
		for (int i = 0; i < pension.size(); i++) {
			(pension.get(i)).toString(); 
		}
	}
	public void eat(){
		for (int i = 0; i < pension.size(); i++) {
			(pension.get(i)).eat(); 
		}
	}
	public void fly(){
		for (int i = 0; i < pension.size(); i++) {
			Animals tempAnimal = pension.get(i);
			if (tempAnimal instanceof Birds) {
				Birds(tempAnimal).fly();
			}
		}
	}
	public void bark(){
		for (int i = 0; i < pension.size(); i++) {
			Animals tempAnimal = pension.get(i);
			if (tempAnimal instanceof Dog) {
				Birds(tempAnimal).bark();
			}
		}
	}
}