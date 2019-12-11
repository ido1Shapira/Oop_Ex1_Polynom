package test;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;

class Function_GUI_complexFunction_Test {
	
	@Test
	void testDrawFromJcon() {
		Functions_GUI cfColect = new Functions_GUI();

		ComplexFunction cf = new ComplexFunction(new Polynom ("x^2+2x+1"));
		ComplexFunction cf1 = new ComplexFunction(new Polynom ("x^2+8x+16"));
		ComplexFunction cf2 = new ComplexFunction(new Polynom ("4x-3"));
		ComplexFunction cf3 = new ComplexFunction(new Polynom ("1"),new Monom("x"),"div" );

		cfColect.add(cf);
		cfColect.add(cf1);
		cfColect.add(cf2);
		cfColect.add(cf3);
		cfColect.drawFunctions("myParams.json");
	}
	
	
	@Test
	void testDraw() {
		Functions_GUI cfColect = new Functions_GUI();
		cfColect.initFromFile("function_file.txt");
		cfColect.drawFunctions(800,600,new Range(-10,10),new Range(-5,15),1000);
		cfColect.initFromFile("morefunc.txt");
		cfColect.drawFunctions(800,600,new Range(-10,10),new Range(-5,15),5000);
		try {
			cfColect.saveToFile("edut.txt");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	@Test
	void testdiv() throws Exception{
		ComplexFunction cf = new ComplexFunction(new Polynom ("x^4+x^3-27x^2-25x+50"),new Polynom("x^3-4x^2-7x+10") , "div");
		System.out.println(cf.equals(new Polynom("x+5")));
		
	}

public static final double EPS = 0.00001;

@Test
void test() {
	Monom m1 = new Monom(2,2);
	Monom m2 = new Monom(3,3);
	ComplexFunction cf = new ComplexFunction("plus", m1,m2);
	cf.mul(m2);
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

@Test
void testOfString() {
	Polynom p1 = new Polynom();
	p1.add(new Monom(2,2));
	Polynom p2 = new Polynom();
	p2.add(new Monom(3,3));
	Monom m1 = new Monom(2,2);
	Monom m2 = new Monom(3,3);
	ComplexFunction cf = new ComplexFunction("plus", m1,m2);
	ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);
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

@Test
void testcomplexFunction() {
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
