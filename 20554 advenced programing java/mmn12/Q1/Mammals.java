package mmn12Q1;

public abstract class Mammals extends Animals {

	protected Owner _owner;
	
	public Mammals(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this._owner = owner;
	}
	
	//equals @override
	public boolean equals(Object anotherMammal){
		if(anotherMammal instanceof Mammals &&
			super.equals(anotherMammal) && (this.getOwner()).equals(((Mammals)anotherMammal).getOwner()))
				return true;
		return false;
	}
	public String getOwner(){
		return this._owner.toString();
	}
	public void setOwner(Owner owner){
		this._owner = owner;
	}	
	public String toString() {
		return "Mammal: " +  "\n" +
					super.toString();
	}

}
