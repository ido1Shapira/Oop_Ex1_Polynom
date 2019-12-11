package test;

import java.util.Scanner;

import myMath.Monom;
import myMath.Polynom;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}
	private static void test3() {
		System.out.println("*****  Test4:  *****");
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
		System.out.println("now you try! please enter a polynom");
		Scanner sc = new Scanner(System.in);
		String tryMy= sc.next();
		while (!tryMy.equals("s")) {
			try {
				new Polynom(tryMy);
				System.out.println("it was a good one!");
			}
			catch(Exception e) {
				System.out.println("it wasn't a legal polynom :(");
			}
			System.out.println("please enter another polynom , to stop type s");
			tryMy=sc.next();
		}
		sc.close();
	}
	private static void test4() {
		System.out.println("*****  Test4:  *****");
		System.out.println("some good examples of polynnoms - ");
		Polynom p1 = new Polynom("3x^2+x^3+4");
		Polynom p2 = new Polynom("4*x^3+x^2-5X+1");
		Polynom p3 = new Polynom("8X^3-5");
		Polynom p4 = new Polynom("4X-2X^2-2X+5+7");
		System.out.println("p1 is: "+p1.toString()+ "\np2 is: "+p2.toString()
		+ "\np2 is: "+p2.toString()+ "\np2 is: "+p2.toString());
		p1.add(p2);
		System.out.println("the add method: p1=p1+p2 \np1="+p1.toString());
		p2.substract(p3);
		System.out.println("the substract method: p2=p2-p3 \np2="+p2.toString());
		p3.multiply(p3);
		System.out.println("the multiply method: p3=p3*p3 \np3="+p3.toString());
		System.out.println("the f(x) method: p4(3)="+p4.f(3));
		
		p1=new Polynom("2+3X^2+x^3-5");
		p2=new Polynom("X^3+3X^2-3");
		System.out.println("p1 now is: "+p1.toString()+ "\np2 now is: "+p2.toString());
		System.out.println("is p1 logically equal to p2? "+p1.equals(p2) );
		
		p3= new Polynom ("x^2-8*X+15"); //x=5, 3
		System.out.println("p3="+p3.toString());
		System.out.println("p3 roots are x=5, x=3");
		System.out.println("p3 root between x=1 and x=4 suppose to be 3");
		System.out.println("p3.root(1, 4, epsilon) = " + p3.root(1, 4, Monom.EPSILON));
		System.out.println("the area above x axis between x=0 and x=5 is: " + p3.area(0, 5, Monom.EPSILON));
		System.out.println("p7 derivative is= "+p3.derivative());
	}
	public static void test1() {
		System.out.println("*****  Test1:  *****");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		System.out.println(p1.toString());
	}
	public static void test2() {
		System.out.println("*****  Test2:  *****");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.2x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
	}
}
