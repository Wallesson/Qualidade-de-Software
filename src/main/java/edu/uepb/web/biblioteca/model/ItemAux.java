package edu.uepb.web.biblioteca.model;

public class ItemAux {
	boolean equalsAux(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	public int hashcodeAux(int id) {

		int result = 1;
		result = result + id;
		return result;
	}
	public String toString() {
		return "Item ";
	} 
}
