package mmn11Q1;

enum cardType { 
	CLUD,
	DIAMOND,
	HEART,
	SPADE
}
public class Card {
	private int _value;
	private cardType _cardType;
	
	public Card(int value, cardType cardType) {
		this._value = value;
		this._cardType = cardType;
		
	}

	/* print card content */
	public String toString() {
		String res = "_value is: "+this._value+" _cardType is: "+ this._cardType;
		return res;
	}
	/* get the value of card */ 
	public int getValue() {
		return this._value;
	}
}
