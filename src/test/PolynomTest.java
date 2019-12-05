package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;
import myMath.function;

class PolynomTest {

	@Test
	void testPolynomString() {
		System.out.println("some examples of polynoms - "
				+ "x7, x^3*x-5,(3+4)*x^2 , x^5-x^-1,y^2+2y-1,3x^2+X^3+4, "
				+ "4*x^3+2x^2-5X+1, 8X^3-5, 4X-2X^2-2X+5+7");
		String[]polynomsArr = {"x7", "3X^5-2x+5","4*x^3+2x^2-5X+1","8X^3-5","4X-2X^2-2X+5+7",
				"x^3*x-5", "4*X-2X+5", "x^4-x^3" ,
				"(3+4)*x^2" , "x^5-x^-1","y^2+2y-1","3x^2+X^3+4",};
		for (int i = 0; i < polynomsArr.length; i++) {
			try {
				Polynom p = new Polynom(polynomsArr[i]);
				System.out.println(p.toString()+" is a legal polynom :)\n");
			} catch(Exception e) {
				System.out.println(polynomsArr[i]+" is illegal polynom :(\n");
			}
		}
	}

	@Test
	void testF() {
		Polynom p3 = new Polynom("8X^3-5");
		double actual = p3.f(2);
		double expected = 59;
		assertEquals(expected, actual, "f(x)\np(3) expected= "+ expected + " got: " +actual);

	}

	@Test
	void testAddPolynom_able() {
		Polynom p1 = new Polynom("3x^2+x^3+4");
		Polynom p2 = new Polynom("4*x^3+x^2-5X+1");
		Polynom expected = new Polynom("5x^3+4x^2-5x+5");
		p1.add(p2);
		if(!p1.equals(expected))
			fail("add Polynoms, expected="+ expected+ " got: "+ p1);
	}

	@Test
	void testAddMonom() {
		Polynom p = new Polynom("2x^2-1");
		Monom m = new Monom("2x");
		Polynom expected = new Polynom("2x^2+2x-1");
		p.add(m);
		if(!p.equals(expected))
			fail("add monom to polynom faild, expected="+ expected+ " got: "+ p);
	}

	@Test
	void testSubstract() {
		Polynom p1 = new Polynom("3x^2+x^3+4");
		Polynom p2 = new Polynom("4*x^3+x^2-5X+1");
		Polynom expected = new Polynom("-3x^3+2x^2+5x+3");
		p1.substract(p2);
		if(!p1.equals(expected))
			fail("substract Polynoms, expected="+ expected+ " got: "+ p1);
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom p1 = new Polynom("x+2");
		Polynom p2 = new Polynom("x-3");
		Polynom expected = new Polynom("x^2-x-6");
		p1.multiply(p2);
		if(!p1.equals(expected))
			fail("multiply Polynoms, expected="+ expected+ " got: "+ p1);	
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p1 = new Polynom("2x^2-2");
		Polynom p2 = new Polynom("2*x^2-2");
		if(!p1.equals(p2))
			fail("the polynom are needed to be equals: p1="+ p1.toString()+ " p2: "+ p2.toString());	
	}

	@Test
	void testIsZero() {
		Polynom p1 = new Polynom("0");
		if(!p1.equals(Polynom.ZERO))
			fail("the polynom are needed to be equals: p1="+ p1.toString()+ " p2: "+ Polynom.ZERO.toString());
	}

	@Test
	void testRoot() {
		Polynom p3= new Polynom ("x^2-8*X+15"); //x=5, 3
		double expected = 3;
		double actual = p3.root(1, 4, Monom.EPSILON);
		if (expected -  actual > Monom.EPSILON)
			fail("p3 roots are x=5, x=3\nroot expected= "+ expected + " got: " +actual);
	}

	@Test
	void testDerivative() {
		Polynom p = new Polynom("x^3-2x-6");
		Polynom expected = new Polynom("3x^2-2");
		Polynom_able actual = p.derivative();
		if(!actual.equals(expected))
			fail("derivative Polynom, expected="+ expected.toString()+ " got: "+ actual.toString());	
	}

	@Test
	void testArea() {
		Polynom p= new Polynom ("x^2-8*X+15"); //x=5, 3
		double expected = 18.00000075141877; 
		double actual = p.area(0,5, Monom.EPSILON);
		assertEquals(expected, actual, "p area ander the curve between 0 to 5\narea expected= "+ expected + " got: " +actual);
	}

	@Test
	void testMultiplyMonom() {
		Polynom p = new Polynom("2x^2-1");
		Monom m = new Monom("2x");
		Polynom expected = new Polynom("4x^3-2x");
		p.multiply(m);
		if(!p.equals(expected))
			fail("add monom to polynom faild, expected="+ expected+ " got: "+ p);
	}

	@Test
	void testInitFromString() {
		Polynom p=new Polynom("2x^7+2x^2-9");
		function d=p.initFromString(p.toString());
		if(!p.equals(d))
			fail("initFromString failed");
	}

}
