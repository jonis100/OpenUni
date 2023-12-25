package mmn14Q1;

import java.util.*;

public class Set<E> {
	
	private ArrayList<E> _arrList;
	
	// Default empty set constructor
	public Set() {
	
		this._arrList = new ArrayList<E>();
	}
	
	// Parameters constructor
	public Set(E[] Earr) {		 
		//add to _arrList
		this._arrList = new ArrayList<E>();
		for (int i=0; i<Earr.length; i++) {
			if (this.isMember(Earr[i]) == -1)
				this._arrList.add(Earr[i]);
		}
	}
	
	//Union this set and addedSet parameter 
	public void union(Set<E> addedSet) {		
		for (int i=0; i<addedSet.size(); i++) {
			this.insert(addedSet.get(i));
		}
//		this._arrList.addAll(addedSet._arrList); 
	}
	
	//Intersect of this set and parameter set2 
	public void intersect(Set<E> set2) {
		for (int i=0; i<this._arrList.size(); i++) {
			boolean isIntersect = false;
			for (int j=0; j<set2._arrList.size(); j++) {
				if (this._arrList.get(i).equals(set2._arrList.get(j)))
					isIntersect = true;
			}
			if (!isIntersect) {
				this._arrList.remove(i);
				i--;
			}
		}
	}

	//Check if the set parameter is a subset of this
	public boolean isSubset(Set<E> set2) {
		this.intersect(set2);
		if (this._arrList.size() == set2._arrList.size())
			return true;
		return false;
	}
		
	//Check if this parameter is a member in this set return -1 if not exist or index of member existed 
	public int isMember(E member) {
		int isMember = -1;
		for (int i = 0; i<this._arrList.size(); i++) {
			if (this._arrList.get(i).equals(member)) {
				isMember = i;
				return isMember;
			}
		}
		return isMember;
	}
	
	//Insert E member to set if not exist
	public void insert(E member) {
		if ((this.isMember(member) == -1)){
			this._arrList.add(member);
		}
	}
	
	//Delete member (if exist) from the set
	public void delete(E member) {
		int tempIndex = this.isMember(member);
		if (tempIndex != -1) {
			this._arrList.remove(tempIndex);
		}
		return;
	}
	
	//Return iterator over the set
	public Iterator<E> iterator() {
		return this._arrList.iterator();
	}
	
	//@override toString method
	public String toString() {
		return this._arrList.toString();
	}
	
	
	//Debuging methods
	
	//Get(int index)
	protected E get(int i) {
		return this._arrList.get(i);
	}
	
	//Size of Set
	protected int size() {
		return this._arrList.size();
	}
}
