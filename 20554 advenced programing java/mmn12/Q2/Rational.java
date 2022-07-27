package mmn12Q2;

public class Rational {
	protected int _P;
	protected int _Q;
	
	public Rational(int P, int Q) {
		if(Q <= 0) {
			throw new IllegalArgumentException("Can not divde by zero or minus, Try anothr arguments");
		}
		this._P = P;
		this._Q = Q;
	}
	public boolean greaterThen(Rational r) {
		if((this._P*r._Q)>(this._Q*r._P)) {
			return true;
		}
		return false;
	}
	public boolean equals(Rational r) {
		if (this._P == r._P && this._Q == r._Q) {
			return true;
		}
		return false;
	}
	public Rational plus(Rational r) {
		Rational res = new Rational(((this._P*r._Q)+(this._Q*r._P)),(this._Q*r._Q));
		return res;
	}
	public Rational minus(Rational r) {
		Rational res = new Rational(((this._P*r._Q)-(this._Q*r._P)),(this._Q*r._Q));
		return res;
	}
	public Rational multiply(Rational r) {
		Rational res = new Rational((this._P*r._P),(this._Q*r._Q));
		return res;
	}
	public Rational divide(Rational r) {
		if (r._P == 0){
		throw new ArithmeticException("Can not divde by zero , Try anothr Rational number");
		}
		Rational res = new Rational((this._P*r._Q), (r._P*this._Q));
		return res;
	}
	public int getNumerator() {
		return this._P;
	}
	public int getDenominator() {
		return this._Q;
	}
	public String toString() {
		return this._P + "/" + this._Q;
	}
	public Rational reduce() {
		int gcd = gcd(this._P, this._Q);
		Rational res = new Rational(this._P/gcd, this._Q/gcd);
		return res;
	}
	private static  int gcd(int x, int y) {
		if (y == 0) {
			return x; 	
		}
		return gcd(y,x%y);
	}
}
