package mmn11Q1;

import java.util.Collections;
import java.util.LinkedList;

public class Pile {
	private LinkedList<Card> _pileList;

	public Pile() {
		_pileList = new LinkedList<Card>();
	}

	/* return and remove First card of the pile */
	public Card pollFirst() {
		return _pileList.pollFirst();
	}
	/* return only (NOT remove) First card of the pile */
	public Card peek() {
		return _pileList.peek();
	}

	/* generate the pile and shuffle it */
	public void Init() {
		for (int i = 0; i < 13; i++){
			Card CLUDcard = new Card(i+1, cardType.CLUD);
			this._pileList.add(CLUDcard);
			Card DIAMONDcard = new Card(i+1, cardType.DIAMOND);
			this._pileList.add(DIAMONDcard);
			Card HEARTcard = new Card(i+1, cardType.HEART);
			this._pileList.add(HEARTcard);
			Card SPADEcard = new Card(i+1, cardType.SPADE);
			this._pileList.add(SPADEcard);
			Collections.shuffle(this._pileList);
		}
	}

	/* return the first half of a pile from index 0 to (size of pile/2)-1)
	 * assuming Pile size is even */
	public Pile first_half_pileList() {
		Pile firstHalfP = new Pile();
		for (int i = 0;i < this._pileList.size()/2; i++) {
			firstHalfP._pileList.add(this._pileList.get(i));
		}
		return firstHalfP;
	}
	/* return the second half of a pile from index to size of pile/2, size of pile */
	public Pile second_half_pileList() {
		Pile secondHalfP = new Pile();
		for (int i = this._pileList.size()/2; i < this._pileList.size(); i++) {
			secondHalfP._pileList.add(this._pileList.get(i));
		}
		return secondHalfP;
	}

	/* return the size-amount of cards in a pile */
	public int size() {
		return this._pileList.size();
	}

	/* attach the inner pile */
	public void attach_pile_to_button(Pile pile) {
		for (int i=0;i<pile.size();i++) {
			this._pileList.add(pile.pollFirst());
			}
	}	
	public void addFirst(Card card) {
		this._pileList.addFirst(card);
	}
	public void addLast(Card card) {
		this._pileList.addLast(card);
	}
	public LinkedList<Card> getAll(){
		return this._pileList;
	}
	public void addAll(Pile pile) {
		this._pileList.addAll(pile.getAll());
	}
	public void add(Card card) {
		this._pileList.add(card);
	}

}
