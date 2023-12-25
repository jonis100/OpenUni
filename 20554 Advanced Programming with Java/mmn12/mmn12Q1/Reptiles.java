package mmn12Q1;

public abstract class Reptiles extends Animals{
	
	protected boolean _hasLegs;
	
	public Reptiles(String name, int age, String color, boolean hasLegs) {
		super(name, age, color);
		this._hasLegs = hasLegs;
	}
	
	public abstract void crawl();
}
