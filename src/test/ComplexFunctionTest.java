package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Monom;
import myMath.Operation;
import myMath.Polynom;
import myMath.function;

class ComplexFunctionTest {

	@Test
	void testComplexFunctionFunctionFunctionOperation() {
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		ComplexFunction cf2 = new ComplexFunction(m1,m2, Operation.Plus);
		if(!cf.equals(cf2))
			fail("suppose to be equal");
	}


	@Test
	void testGetLeft() {
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		cf.mul(m2);
		function f=(function) cf.getLeft();
		if (f instanceof Monom) {
			Monom m=(Monom)f;
			if(!m.equals(m1)){
				fail("suppose to be equal");
			}
		}	
	}


	@Test
	void testF() {
		Polynom p1=new Polynom("x^2+5x-9");
		Polynom p2=new Polynom("4x^2-7x+20");
		ComplexFunction cf=new ComplexFunction(p1, p2, Operation.Times);
		double expected=p1.f(3)*p2.f(3);
		double actuel =cf.f(3);
		if(expected!=actuel)
			fail("f(x) test failed");
	}

	@Test
	void testToString() { 
		Polynom p1=new Polynom("x^2+3x-5");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "comp");
		String expected="comp(X^2+3.0X-5.0,3.0X^2-7.0X)";
		String actuel=cf.toString();
		if(!expected.equalsIgnoreCase(actuel))
			fail("toString failed");
	}

	@Test
	void testInitFromString() {
		Polynom p1=new Polynom("x^2+3x-5");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "comp");
		function cf1=cf.initFromString("plus(min(x,2x),max(3x^2+7x, 120))");
		Polynom p3=new Polynom("x");
		Polynom p4=new Polynom("2x");
		Polynom p5=new Polynom("3x^2+7x");
		Polynom p6=new Polynom("120");
		ComplexFunction cf2=new ComplexFunction(p3, p4, "min");
		ComplexFunction cf3=new ComplexFunction(p5, p6, "max");
		ComplexFunction cf4=new ComplexFunction(cf2, cf3, "plus");
		if(!cf4.equals(cf1))
			fail("init from string failed");
	}

	@Test
	void testEqualsComplexFunction() {
		Polynom p1=new Polynom("x^2+3x-5+2");
		Polynom p2=new Polynom("3.0000001x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "plus");
		ComplexFunction cf2=new ComplexFunction(p1, p2, Operation.Plus);
		if(!cf.equals(cf2)) {
			fail("equals failed");
			System.out.println("fail!!");
		}
	}

	@Test
	void testCopy() {
		Polynom p1=new Polynom("x^2+3x-3");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "plus");
		function cfcopy=cf.copy();
		if(!cfcopy.equals(cf))
			fail("should be equals!");
		if(cfcopy instanceof ComplexFunction)
			((ComplexFunction)cfcopy).mul(p1);
		if(cfcopy.equals(cf))
			fail("should be differante!");
	}

	@Test
	void testPlus() {
		Polynom p1=new Polynom("x^3+73x-8");
		Polynom p2=new Polynom("3x^6-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "plus");
		p1.add(p2);
		if(!p1.equals(cf))
			fail("should be equal!");
	}

	@Test
	void testMul() {
		Polynom p1=new Polynom("x^2+3x-3");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "mul");
		p1.multiply(p2);
		System.out.println(p1+"    "+cf);
		if(! p1.equals(cf))
			fail("should be equal!");
	}

	@Test
	void testDiv() {
		Polynom p1=new Polynom("x^2+3x-3");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "mul");
		cf.div(p2);
		if(!p1.equals(cf))
			fail("div failed");
	}

	@Test
	void testMax() {
		Polynom p1=new Polynom("x^2+5x+6");
		Polynom p2=new Polynom("0");
		ComplexFunction cf=new ComplexFunction(p1, p2, "max");
		if((cf.f(-2.5)!=p2.f(-2.5)) || (cf.f(5)!=p1.f(5)))
			fail("max failed");
	}

	@Test
	void testMin() {
		Polynom p1=new Polynom("x^2+5x+6");
		Polynom p2=new Polynom("0");
		ComplexFunction cf=new ComplexFunction(p1, p2, "min");
		if((cf.f(-2.5)!=p1.f(-2.5)) || (cf.f(5)!=p2.f(5)))
			fail("min failed");
	}

	@Test
	void testComp() {
		Polynom p1=new Polynom("x^2+3x-3");
		Polynom p2=new Polynom("3x^2-7x");
		ComplexFunction cf=new ComplexFunction(p1, p2, "comp");
		double rightAns=p2.f(8);
		if(p1.f(rightAns)!=cf.f(8))
			fail("comp failed");
	}


}
