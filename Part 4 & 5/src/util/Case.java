package util;

public class Case {
	public final int absc;
	public final int ord;
	
	public Case(int absc, int ord) {
		super();
		this.absc = absc;
		this.ord = ord;
	}

	public boolean equals(Case other) {
		return this.absc == other.absc && this.ord == other.ord;
	}

}
