
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Ido Shapira & Edut Cohen 
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	/**
	 * Contractor function
	 * @param a the coefficient of the monom
	 * @param b	the power value of the monom
	 */
	public Monom(double a, int b){
		if (b<0) {throw new IllegalArgumentException("exponent cannot be negative: " + b);}
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * deep copy constructor, copies the given monom to a similar new one
	 * @param ot the monom we want to duplicate
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * this method returns the coefficient of the monom we are working on
	 * @return  our monom's coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * this method returns the power value of the monom we are working on
	 * @return our monom's power
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative function of the monom we are working on.
	 * @return our monom's derivative function
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 *this method returns the value of our monom in a given x value
	 *@param x the x we want to know the monom's value in
	 *@return the monom's value within the given x 
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/**
	 * this method checks whether our monom is the Zero monom defined at the beginning of the code 
	 * @return true if it's the zero monom, false otherwise
	 */
	public boolean isZero() {
		return this.get_coefficient() == 0;}
	/**
	 * the method checks if we can build a new monom from a given string according to the form of a*x^b when a is a real number and b is a non negative integer
	 * @param s the string we now check
	 * @return true is s is a legal form of a monom, false otherwise.
	 */
	private boolean isTheMonomValid (String s) {  //Checking the integrity of any string input
		int count = 0;
		for (int i=0;i<s.length() && count<=1;i++) {
			char ch = s.charAt(i);
			if (ch == ',') 
				return false;
			if (ch < '*' || ch > '9') {
				if (ch == '*') {count++;}
				if (ch != '^' && ch != 'X')
					return false;
			}
		}
		if (s.charAt(s.length()-1) == '*' || count >1)
			return false;
		return true;
	}
	/**
	 * this method is a sub- method of isTheMonomValid.
	 * this method checks whether the coefficient is legal. 
	 * @param s the may-be coefficient we now check
	 * @return true if s is a legal coefficient, false otherwise.
	 */
	private boolean isCoefficientValid (String s) {
		int count = 0;
		for (int i=0;i<s.length() && count <= 1;i++) {
			char ch = s.charAt(i);
			if (ch == '.') {
				count++;
			}
			if (ch == '^') return false;
		}
		if (count == 1)
			if (s.charAt(s.length()-1) == '.')
				return false;
		return (count <= 1) ? true : false;
	}
	/**
	 * this method is a sub- method of isTheMonomValid.
	 * this method checks whether the power value is legal. 
	 * @param s the may-be power value we now check
	 * @return true if s is a legal power value, false otherwise.
	 */	
	private boolean isPowerValid (String s) {
		for (int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if (ch < '0' || ch > '9')
				return false;
		}
		return true;
	}
	/**
	 * this method builds a new monom according to a given string in case the string returned true from the isTheMonomValid function
	 * @param s the string we check its legacy and build a monom from. 
	 */
	public Monom(String s) {
		int len = s.length();
		if (s == "") {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
		s = s.replaceFirst("x", "X");
		s = s.replaceAll(" ", "");
		if (!isTheMonomValid(s)) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
		if (s.contains("X")) {
			if (s.contains("^")) {
				if (len == 2) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
				if (s.indexOf('^') - 1 != s.indexOf('X')) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");} //must be like "X^"}
				String b = s.substring(s.indexOf('^') +1);
				if (isPowerValid (b)) {
					this.set_power(Integer.parseInt(b));
				}
				else {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
			}
			else { //does not contain '^'
				if (s.indexOf('X') + 1 != s.length()) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
				this.set_power(1);
			}
			String a = "";
			if (s.contains("*")) {
				if (len == 2) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
				if (s.indexOf('*') + 1 != s.indexOf('X')) {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");} //must be like "*X"}
				a = s.substring(0,s.indexOf("*"));
			}
			else { //not contains '*'
				if(!s.equals("X"))
					a = s.substring(0,s.indexOf("X"));
			}
			if (a == "") 
				this.set_coefficient(1);
			else {
				if (isCoefficientValid(a)) {
					if (s.contains("-X")) {
						this.set_coefficient(-1);
					}
					else {
						if (s.charAt(0) == 'X') {
							this.set_coefficient(1);
						}
						else {
							this.set_coefficient(Double.valueOf(a));
						}
					}
				}
				else {throw new IllegalArgumentException("Invalid input:" + s + "\n valid monom is a*x^b");}
			}
		}
		else {
			this.set_coefficient(Double.valueOf(s));
			this.set_power(0);
		}
	}
	/**
	 * this method adds the given monom to our monom if it is possible (the power values are equal)
	 * @param m the monom we want to add
	 */
	public void add(Monom m) {
		if (this.isZero()) {
			this.set_coefficient(m.get_coefficient());
			this.set_power(m.get_power());
		}
		if (this.get_power() != m.get_power()) {throw new RuntimeException("exponent values are not equal");}
		this.set_coefficient(this.get_coefficient() + m.get_coefficient());
	}
	/**
	 *this method multiplies the given monom by our monom and save the result in our monom  
	 * @param d the monom we want to multiply by
	 */
	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient() * d.get_coefficient());
		this.set_power(this.get_power() + d.get_power());
	}
	/**
	 * this method returns a string represents the logical value of our monom
	 */
	
	public String toString() {
		double a = this.get_coefficient();
		int b = this.get_power();
		if (a == 0) return "0";
		StringBuilder ans = new StringBuilder();
		if (b==0) return "" + a;
		if (a != 1) {
			if (a == -1) {
				if (b !=0) ans.append('-');
				else {
					ans.append(a);
					return ans.substring(0);
				}
			}
			else ans.append(a);
		}
		if (b == 1) {
			ans.append('X');
			return ans.substring(0);
		}
		else {
			ans.append("X^" + b);
			return ans.substring(0);
		}
	}
	/**
	 * this method changes our monom's coefficient to the given double
	 * @param a the coefficient value we want 
	 */
	public void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * this method changes our monom's power value to the given int if (only if it's a non negative int according to the monom legal form)
	 * @param p the power value we want 
	 */	
	public void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	/**
	 * this method creates and returns a new zero monom
	 * @return a new zero monom
	 */
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	/**
	 * this method checks whether the given monom and our monom are logically equals   
	 * @param m the monom we are comparing to
	 * @return true if there are equals false otherwise
	 */
	public boolean equals(Monom m) {
		if (Math.abs(this.get_coefficient()) <=Monom.EPSILON && Math.abs(m.get_coefficient()) <= Monom.EPSILON) return true;
		return (Math.abs((this.get_coefficient() - m.get_coefficient())) <= Monom.EPSILON);
	}
	public boolean equals(Object f) {
		if(f instanceof Monom )
			return this.equals((Monom) f);
		if(f instanceof Polynom) {
			Polynom copyM = new Polynom(this.toString());
			return ((Polynom) f).equals(copyM);
			}
		if (f instanceof ComplexFunction) {
			ComplexFunction cf=new ComplexFunction(this);
			return cf.equals(f);
		}
		return false;
	}
	private double _coefficient; 
	private int _power;
	/**
	 * this method calls the constructor from a string for monom
	 */
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}
	/*
	 * copy constructor
	 */
	@Override
	public function copy() {
		return new Monom(this.toString());
	}
	
	
}
