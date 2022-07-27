package mmn15Q2;

public class MyMonitor {

	private int _n;
	private int _monitorCount;

	public MyMonitor(int n) {
		
		this._n = n;
		this._monitorCount=0;
	}

	public synchronized void waitForAll() {
//		System.out.println("Here****** n :" +_monitorCount);  GOOD

		while (this._monitorCount<this._n) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void CulculateDone() {

		this._monitorCount++;
		notifyAll(); 
	}
}


