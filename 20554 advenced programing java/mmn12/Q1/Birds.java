package mmn12Q1;

public abstract class Birds extends Animals{
	protected boolean _canFly;
	protected double _wingspan;

	public Birds(String name, int age, String color, boolean canFly, double wingspan) {
		super(name, age, color);
		this._canFly = canFly;
		this._wingspan = wingspan;
	}
	
	//setters
	public void setCanFly(boolean canFly){
		this._canFly = canFly;
	}
	public void setWingspan(float wingspan){
		this._wingspan = wingspan;
	}
	//getters
	public double getWingspan(){
		return this._wingspan;
	}
	public void fly(){
		if (this._canFly)
			System.out.println("Hi there, I'm a Bird so I can fly..");
		System.out.println("Although I'm a Bird, I can't fly..");
	}

}
