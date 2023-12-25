package mmn15Q2;

public class Thr extends Thread{
	
	private int _value;
	private int _position;
	private MyMonitor _monitor;
	private Thr _myThreads[];
	private int _chacked;

	public Thr(int value, int position, MyMonitor monitor, Thr[] myThreads) {
		this._value = value;
		this._position = position;
		this._monitor = monitor;
		this._myThreads = myThreads;
		this._chacked = 0;
	}
	
	public void run() {
		super.run();
		int temp = calaulate();
		this._monitor.CulculateDone();
		this._monitor.waitForAll();
		this._value = temp;
//		System.out.print("In thread: ");
//		this.printArr();
		
	}
	
	
	//calculate the desire value per value in threads array and return it
	public int calaulate() {

		int n = this._myThreads.length;
		int Iminus1 = (this._position+n-1)%n;	
		int Iplus1 = (this._position+1)%n;	
		int temp = 0;
		if (this._value>this._myThreads[Iminus1].get_value() && this._value>this._myThreads[Iplus1].get_value()) {
			temp--;
		}
		if (this._value<this._myThreads[Iminus1].get_value() && this._value<this._myThreads[Iplus1].get_value()) {
			temp++;
		}
		return this._value+temp;
		
	}
	
	
	//printing the values of threads array
	public void printArr() {

		for (Thr i:this._myThreads) {
			System.out.print(i.get_value()+ " ");
		}
		System.out.print("\n");
	}
	
	// change the value of thread
	public void change(int value) {
		this.set_value(value);
	}
	
	public int get_value() {
		return this._value;
	}

	public void set_value(int _value) {
		this._value = _value;
	}
	
	public void check() {
		this._chacked += 1;
	}
	
	public boolean isChacked() {
		if (this._chacked == 2)
			return true;
		return false;
	}
	

}
