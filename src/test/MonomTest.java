package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.function;

class MonomTest {
	@Test
	void testDerivative() {
		Monom m1 = new Monom ("2x^5");
		Monom expected = new Monom ("10x^4");
		Monom actual = m1.derivative();
		if (!actual.equals(expected))
			fail("m1 not equals to m3 ,expected =" + expected.toString() + " got: " + actual.toString());
	}

	@Test
	void testF() {
		String[] monoms = {"34","x","2*x","3*X", "-x","-3.2*x^2","4","0*x^5","2*x^2","3.56*x^4","x^6"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
		double expected = 21;
		double actual = new Monom ("-3*X").f(-7);
		
		assertEquals(expected, actual, "Test f(x)");
		
	}

	@Test
	void testIsZero() {
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
		Monom m1 = new Monom ("0");
		if (!m1.isZero())
			fail("the monom not zero");
	}

	@Test
	void testMonomString() {
		String[]monomsArr = {"x^5","x*6", "4x^7", "3*x^2","y^5","4*x^-1","  7x^8"};
		for (int i = 0; i < monomsArr.length; i++) {
			try {
				Monom m = new Monom(monomsArr[i]);
				System.out.println(m.toString()+" is a legal monom :)\n");
			} catch(Exception e) {
				System.out.println(monomsArr[i]+" is illegal monom :(\n");
			}
		}
	}

	@Test
	void testAdd() {
		Monom m1 = new Monom ("2x");
		Monom m2 = new Monom ("-3x");
		Monom m3 = new Monom ("-x");
		m1.add(m2);
		if (!m1.equals(m3))
			fail("m1 not equals to m3 ,expected =" + m3.toString() + "got: " + m1.toString());
	}

	@Test
	void testMultipy() {
		Monom m1 = new Monom ("2x");
		Monom m2 = new Monom ("-3x");
		Monom m3 = new Monom ("-6x^2");
		m1.multipy(m2);
		if (!m1.equals(m3))
			fail("m1 not equals to m3 ,expected =" + m3.toString() + "got: " + m1.toString());
	}

	@Test
	void testToString() {
		String expected = new Monom ("3*x^2").toString();
		String actual = new Monom ("3.0x^2").toString();
		assertEquals(expected, actual, "monom.toString, expected = "+ expected + " got: " + actual);
	}

	@Test
	void testEqualsMonom() {
		Monom m1 = new Monom ("2x");
		Monom m2 = new Monom ("2*x");
		if (!m1.equals(m2))
			fail("m1 not equals to m3 ,expected =" + m2.toString() + "got: " + m1.toString());
	}
	@Test
	void testInitFromString() {
		Monom m=new Monom("2x^7");
		function d=m.initFromString(m.toString());
		if(!m.equals(d))
			fail("initFromString failed");
	}

	@Test
	void testCopy() {
		Monom m=new Monom ("2x");
		function f=m.copy();
		m.add(new Monom ("4x"));
		if(f.equals(m))
			fail("copy failed");
	}

}
