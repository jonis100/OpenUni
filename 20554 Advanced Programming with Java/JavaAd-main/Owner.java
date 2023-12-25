package mmn12Q1;

public class Owner {
	protected String _name;
	protected double _phoneNumber;


public void setName(String name, double phoneNumber) {
	this._name = name;
	this._phoneNumber = phoneNumber;
	}

public void get() {
	System.out.println("Owner name: " + this._name);
	System.out.println("Owner phoneNumber: " + this._phoneNumber);
	}


}
