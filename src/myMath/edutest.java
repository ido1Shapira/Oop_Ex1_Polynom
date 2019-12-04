package myMath;

import java.io.IOException;

public class edutest {

	public static void main(String[] args) {
//		test();
//		testOfString();
//		testcomplexFunction();
		testDraw();
//		try {
//			testdiv();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	private static void testDraw() {
		CollectionFunctions cfColect = new CollectionFunctions();
//		try {
//			cfColect.initFromFile("function_file1.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ComplexFunction cf = new ComplexFunction(new Polynom ("x^2+2x+1"));
		ComplexFunction cf1 = new ComplexFunction(new Polynom ("x^2+8x+16"));
		ComplexFunction cf2 = new ComplexFunction(new Polynom ("4x-3"));
		ComplexFunction cf3 = new ComplexFunction(new Polynom ("1"),new Monom("x"),"div" );

		cfColect.add(cf);
		cfColect.add(cf1);
		cfColect.add(cf2);
		cfColect.add(cf3);
		cfColect.drawFunctions(800,600,new Range(-10,10),new Range(-5,15),1000);
		try {
			cfColect.saveToFile("edut.txt");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	private static void testdiv() throws Exception{
		ComplexFunction cf = new ComplexFunction(new Polynom ("x^4+x^3-27x^2-25x+50"),new Polynom("x^3-4x^2-7x+10") , "div");
		System.out.println(cf.equals(new Polynom("x+5")));
		
	}
/**
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

	}
};
}
**/
public static final double EPS = 0.00001;
public static void test() {
	Monom m1 = new Monom(2,2);
	Monom m2 = new Monom(3,3);
	ComplexFunction cf = new ComplexFunction("plus", m1,m2);
	//	System.out.println(cf);
	cf.mul(m2);
	//	System.out.println(cf);
	Polynom p = new Polynom();
	p.add(m1);
	p.add(m2);
	p.multiply(m2);
	double v = 4.0;
	double dp = p.f(v);
	double dcf = cf.f(v);
	double dd = Math.abs(dp-dcf);
	if(dd>EPS) {
		System.out.println(p+" at "+v+" = "+dp);
		System.out.println(cf+" at "+v+" = "+dcf);
		System.out.println("ERR: should got the same value from: "+p+"  and "+cf);
	}
	System.out.println("test 1 finish");
}

public static void testOfString() {
	Polynom p1 = new Polynom();
	p1.add(new Monom(2,2));
	Polynom p2 = new Polynom();
	p2.add(new Monom(3,3));
	Monom m1 = new Monom(2,2);
	Monom m2 = new Monom(3,3);
	ComplexFunction cf = new ComplexFunction("plus", m1,m2);
	ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);
	//System.out.println(cf);
	cf.mul(m2);
	cf3.mul(m2);
	String s = cf3.toString();
	function cf2 = cf.initFromString(s);
	if(!cf3.equals(cf2)) {
		System.out.println("ERR: "+cf+" should be equals to "+cf2);
	}
	if(!cf.equals(cf3)) {
		System.out.println("ERR: "+cf+" should be equals to "+cf3);
	}
	System.out.println("test 2 finish");

}
public static void testcomplexFunction() {
	String s1 = "3.1 +2.4x^2 -x^4";
	String s2 = "5 +2x -3.3x +0.1x^5";
	String[] s3 = {"x -1","x -2", "x -3", "x -4"};
	Polynom p1 = new Polynom(s1);
	Polynom p2 = new Polynom(s2);
	Polynom p3 = new Polynom(s3[0]);
	for(int i=1;i<s3.length;i++) {
		p3.multiply(new Polynom(s3[i]));
	}
	ComplexFunction cf = new ComplexFunction("plus", p1,p2);
	ComplexFunction cf4 = new ComplexFunction("div", new Monom("x"),p3);
	cf.div(p1);
	String s = cf.toString();
	function cf5 = cf4.initFromString(s);
	if(!cf.equals(cf5)) {
		System.out.println("ERR: "+cf+" should be equals to "+cf5);
	}
	int size=10;
	for(int i=0;i<size;i++) {
		double x = Math.random();
		double d = cf.f(x);
		double d5 = cf5.f(x);
		if(Math.abs(d-d5)>Monom.EPSILON)
			System.out.println("NOT GOOD!!!!!!!");
	}
	System.out.println("test 3 finish");
}

}


//Monom m1 = new Monom(2,2);
//Monom m2 = new Monom(3,3);
//ComplexFunction cf = new ComplexFunction("plus", m1,m2);
//function cf2= cf.initFromString("plus(comp(min(x,x+9-x^2-7), max(x^2,2X)), 3x^5)");
//function cf3=cf.initFromString(cf2.toString());
//System.out.println(cf2.equals(cf3));
