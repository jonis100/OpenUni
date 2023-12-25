package mmn12Q1;

public class Owner {
	protected String _name;
	protected long _phoneNumber;


	public Owner(String name, long phoneNumber) {
		this._name = name;
		this._phoneNumber = phoneNumber;
	}
	//setters
	public void setName(String name) {
		this._name = name;
	}
	public void setPhoneNumber(long phoneNumber) {
		this._phoneNumber = phoneNumber;
	}
	//getters
	public String getName(String name) {
		return this._name;
	}
	public long getPhoneNumber(long phoneNumber) {
		return this._phoneNumber;
	}

	public String toString() {
		return "Owner name: " + this._name + "\n" +
				"Owner phoneNumber: " + this._phoneNumber;
	}


}
