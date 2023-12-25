package mmn12Q1;

public abstract class Birds extends Animals{
	protected boolean _CanFly;
	protected float _Wingspan;

	public void fly(){
		if (this._CanFly)
			System.out.println("Hi there, I'm a Bird so I can fly..")
		System.out.println("Although I'm a Bird, I can't fly..")
	}

}
