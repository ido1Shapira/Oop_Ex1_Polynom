package myMath;

import java.util.ArrayList;
import java.util.Iterator;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Ido Shapira and Edut Cohen
 *
 */
public class Polynom implements Polynom_able{

	/**
	 * Zero (empty polynom)
	 */
	public static final Polynom ZERO = new Polynom("0");
	private ArrayList<Monom> pol;

	/**
	 * default constructor
	 * creates a zero monom as a default polynom 
	 */
	public Polynom() {
		this.pol = new ArrayList<Monom>();
		this.pol.add(new Monom (0,0));
		//		this.add(Polynom.ZERO);
	}
	/**
	 * init a Polynom from a String such as:[q
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		this.pol = new ArrayList<Monom>();
		s = s.replaceAll(" ", "");
		s = s.replaceAll("-", "\\+-");
		String[] split_s = s.split("\\+");
		int i = (s.charAt(0) == '+') ? 1 : 0;
		while(i < split_s.length) {
			this.add(new Monom (split_s[i]));
			i++;
		}
	}
	@Override
	/**
	 *this method returns the value of our polynom in a given x value
	 *@param x the x we want to know the polynom's value in
	 *@return the polynom's value within the given x 
	 */
	public double f(double x) {
		double ans = 0;
		for (int i = 0;i<this.pol.size();i++) {
			ans  = ans + this.pol.get(i).f(x);
		}
		return ans;
	}
	/**
	 * this method adds the given polynom to our polynom
	 * @param p1 the polynom we want to add
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom temp =it.next();
			this.add(temp);
			this.cleanPol();
		}
	}
	/**
	 *this method adds the given monom to our polynom
	 * @param m1 the monom we want to add
	 */
	@Override
	public void add(Monom m1) {
		Monom mcopy=new Monom(m1);
		int b1 = mcopy.get_power();
		boolean added = false;
		for (int i = 0;i<this.pol.size() && !added;i++) {
			if (b1 == this.pol.get(i).get_power()) {
				this.pol.get(i).set_coefficient(this.pol.get(i).get_coefficient() + mcopy.get_coefficient());
				added = true;
			}
			if (b1 > this.pol.get(i).get_power()) {
				this.pol.add(i,mcopy);
				added = true;
			}
		}
		if (!added) {
			this.pol.add(mcopy);
		}
		this.cleanPol();
	}
	@Override
	/**
	 * this method subtracts the given polynom to our polynom
	 * @param p1 the polynom we want to subtract
	 */
	public void substract(Polynom_able p1) {
		Polynom_able copy_p = (Polynom_able) p1.copy();
		Iterator<Monom> it = copy_p.iteretor();
		while(it.hasNext()) {
			Monom temp =it.next();
			temp.multipy(Monom.MINUS1);
			this.add(temp);
		}
		this.cleanPol();
	}
	private void cleanPol()
	{
		for (int i = 0; i < this.pol.size(); i++) {
			if (this.pol.get(i).isZero()) this.pol.remove(i);
		}
		if (pol.size() == 0) this.pol.add(Monom.ZERO);
	}
	/**
	 * this method multiply the given polynom to our polynom
	 * @param p1 the polynom we want to multiply by
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom_able copy_p1 =(Polynom_able) p1.copy();
		Iterator<Monom> it = copy_p1.iteretor();
		Polynom_able copy_this =this.copy();
		while(it.hasNext()) {
			Polynom_able orginal = (Polynom_able) copy_this.copy();
			Monom m =it.next();
			orginal.multiply(m);
			this.add(orginal);
		}
		this.substract(copy_this);
		this.cleanPol();
	}

	/*
	 * this method checks whether the given polynom and our polynom are logically equals   
	 * @param p1 the polynom we are comparing to
	 * @return true if there are equals false otherwise
	 */
	public boolean equals(Polynom_able p1) {
		Polynom thiscopy= new Polynom(this.toString());
		thiscopy.substract(p1);
		for (int i = 0; i < thiscopy.pol.size(); i++) {
			if(Math.abs(thiscopy.pol.get(i).get_coefficient())>Monom.EPSILON)
				return false;
		}
		return true;
	}
	public boolean equals (Object p1) {
		if (p1 instanceof Polynom_able) {
			return this.equals((Polynom_able) p1);
		}
		if (p1 instanceof Monom) {
			Polynom p=new Polynom(p1.toString());
			return this.equals(p);
		}
		ComplexFunction cf=new ComplexFunction(this, new Polynom("0"), Operation.None);
		return cf.equals(p1);
	}
	/*
	 * this method checks whether the given polynom is zero
	 * @return true if the polynom is zero false otherwise
	 */
	@Override
	public boolean isZero() {
		for (int i = 0; i < this.pol.size(); i++) {
			if(this.pol.get(i).get_coefficient()>Monom.EPSILON)
				return false;
		}
		return true;
	}
	/*
	 * this method takes two values of x and find the root between the two values.
	 * there is a option that the root does't exist
	 * @return the Polynom's root between tow given Xs values.
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(x0 - x1 > 0 && x0 - x1 < Monom.EPSILON ) {throw new IllegalArgumentException("x1 seppose to be greater or equal to x0");}
		double y0 = this.f(x0);
		double y1 = this.f(x1);
		if(y0 * y1 > 0) {throw new IllegalArgumentException("root does not exist");}
		double pos;
		double neg;
		if (y0 < y1) {
			pos = x1;
			neg = x0;
		}
		else {
			pos = x0;
			neg = x1;
		}
		double x_mid = (x0+x1) / 2.0;
		double y_mid = this.f(x_mid);
		if ((y_mid <= eps && y_mid >= -1*eps)) return x_mid;
		if (y_mid<0)
			return root(pos,x_mid,eps);
		else 
			return root (neg,x_mid,eps);
	}
	/*
	 * deep copy, copies the given polynom to a similar new one
	 * @return the same polynom according to the principle of deep copy
	 */
	@Override
	public Polynom_able copy() {
		return new Polynom(this.toString());
	}
	/*
	 *  this method returns the derivative function of the polynom we are working on.
	 * @return our polynom's derivative function
	 */
	@Override
	public Polynom_able derivative() {
		Polynom der = new Polynom();
		for(int i=0;i<this.pol.size();i++)
		{
			der.add(this.pol.get(i).derivative());
		}
		der.cleanPol();
		return der;

	}
	/*
	 * Calculates the area above the x axis between two values of x,
	 * approximately of epsilon
	 * return the area under the curve
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double sum = 0;
		if (x0 >= x1) return sum;
		while(x0 < x1) {
			if (this.f(x0) > 0) {
				sum = sum + this.f(x0)*eps;
			}
			x0 = x0 + eps;
		}
		return sum;
	}
	/*
	 * iterator method
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return pol.iterator();
	}
	/*
	 * the method update our polynom after multiplication with a monom
	 * @param m1 the monom we are multiply with
	 */
	@Override
	public void multiply(Monom m1) {
		Monom mcopy=new Monom(m1);
		double a = mcopy.get_coefficient();
		int b = mcopy.get_power(); 
		for (int i = 0;i<this.pol.size();i++) {

			this.pol.get(i).set_coefficient(a*this.pol.get(i).get_coefficient());
			this.pol.get(i).set_power(b+this.pol.get(i).get_power());
		}
		this.cleanPol();
	}
	/*
	 * this method returns a string represents the logical value of our polynom
	 */
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append(this.pol.get(0).toString());
		for (int i = 1; i < this.pol.size(); i++) {
			if ((this.pol.get(i).get_coefficient() > 0)) {
				ans.append("+" + this.pol.get(i).toString());
			}
			else {
				ans.append(this.pol.get(i).toString());
			}
		}
		return ans.substring(0);
	}
	/**
	 * this method calls the constructor from a string for polynom
	 */
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}

}
