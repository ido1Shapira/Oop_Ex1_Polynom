package myMath;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}
	private static void test4() {
		System.out.println("*****  Test4:  *****");
		Monom m1 = new Monom ("-3*X");
		Monom m2 = new Monom ("4*x");
		Monom m3 = new Monom ("8x^2");
		//f(x):
		System.out.println("m1= "+m1.toString()+",   m2= "+ m2.toString()+",   m3= "+m3.toString());
		System.out.println("m1(1)= "+ m1.f(1));
		System.out.println("m2(2)= "+ m2.f(2));
		System.out.println("m3(3)= "+ m3.f(3));
		//derivative:
		System.out.println("m1'(x) = " + m1.derivative().toString()+"  m2'(x) = " + m2.derivative().toString()+"  m3'(x) = " + m3.derivative().toString());
		//add:
		m1.add(m2);
		System.out.println("m1+m2= "+ m1.toString());
		//Multiply:
		m1.multipy(m3);
		System.out.println("(m1+m2)*m3= "+ m1.toString());
		
	}
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		String[] monoms = {"34","x","2*x","3*X", "-x","-3.2*x^2","4","0*x^5","2*x^2","3.56*x^4","x^6"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
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
	}

	private static void test1() {
		System.out.println("*****  Test1:  *****");
		System.out.println("some examples of monoms - x^5, x*6 4x^7, 3*x^2,y^5, 4*x^-1,  7x^8");
		String[]monomsArr = {"x^5","x*6", "4x^7", "3*x^2","y^5","4*x^-1","  7x^8"};
		for (int i = 0; i < monomsArr.length; i++) {
			try {
				Monom m = new Monom(monomsArr[i]);
				System.out.println(m.toString()+" is a legal monom :)\n");
			} catch(Exception e) {
				System.out.println(monomsArr[i]+" is illegal monom :(\n");
			}
		}	
		System.out.println("now you try! please enter a monom, notice not to add unnecessary spaces");
		Scanner sc = new Scanner(System.in);
		String tryMy= sc.next();
		while (!tryMy.equals("s")) {
			try {
				new Monom(tryMy);
				System.out.println("it was a good one!");
			}
			catch(Exception e) {
				System.out.println("it wasn't a legal monom :(");
			}
			System.out.println("please enter another monom , to stop type s");
			tryMy=sc.next();
		}
		sc.close();
	}
}