package myMath;

public class ComplexFunction implements complex_function{

	private function left;	//left arm of the complex function
	private function right;	//right arm of the complex function
	private Operation op;	// the main operation of complex function

	/**
	 * @param left
	 * @param right
	 * @param op
	 */
	public ComplexFunction(function left, function right, Operation op) {
		this.left = left.copy();
		if(right != null) this.right = right.copy(); 
		else this.right=null;
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
		this.op = op;
	}
	/**
	 * basic constructor 
	 * @param op main operation of the complex function we build
	 * @param left left side of the complex function we build
	 * @param right right side of the complex function we build
	 */
	public ComplexFunction(Operation op, function left, function right) {
		if(left != null) this.left = left.copy();
		if(right != null) this.right = right.copy();
		else this.right=null;
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
		this.op = op;
	}
	/**
	 * constructor for NONE operation
	 * @param f left side of the complex function we build
	 */
	public ComplexFunction (function f)
	{
		this.left=f.copy();
		this.right=null;
		this.op=Operation.None;
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	/**
	 * constructor
	 * @param left left side of the complex function we build
	 * @param right right side of the complex function we build
	 * @param s string that represents the main operation
	 */
	public ComplexFunction(function left, function right, String s){
		this.left = left.copy();
		if(right != null) this.right = right.copy();
		else this.right=null;
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	/**
	 * constructor
	 * @param left left side of the complex function we build
	 * @param right right side of the complex function we build
	 * @param s string that represents the main operation
	 */
	public ComplexFunction(String s, function left, function right){
		this.left = left.copy();
		if(right != null) this.right = right.copy();
		else this.right=null;
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	/**
	 * constructor
	 * @param left left side of the complex function we build
	 * @param right right side of the complex function we build
	 * @param s string that represents the main operation
	 */
	public ComplexFunction(function left,String s,  function right){
		this.left = left.copy();
		if(right != null) this.right = right.copy();
		else this.right=null;
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	/**
	 * checks whether the given string represents a legal operation
	 * @param s the string we now check
	 * @return the operation the string represents
	 */
	private Operation opRecognize (String s) {
		s=s.toLowerCase();
		switch(s) {
		case "plus":
			return Operation.Plus;
		case "comp":
			return Operation.Comp;
		case "div":
			return Operation.Divid;
		case "error":
			throw new IllegalArgumentException("error is not a convential operation");
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "none":	
			return Operation.None;
		case "mul":
			return Operation.Times;
		default:
			throw new IllegalArgumentException("couldn't recognize the operation"); //illegal operation
		}
	}
	/**
	 * 
	 * @return left arm function
	 */
	public function left() {
		return this.left;
	}
	/**
	 * 
	 * @return right arm function
	 */
	public function right() {
		return this.right;
	}
	/**
	 * @return op main operation 
	 */
	public Operation getOp() {
		return op;
	}
	/**
	 *this method returns the value of our complex function in a given x value
	 *@param x the x we want to know the complex function's value in
	 *@return the complex function's value within the given x 
	 */
	@Override
	public double f(double x) {
		switch (this.op){
		case Comp:	//leftf(rightf(x))
			double rightAns=this.right.f(x);
			return this.left.f(rightAns);
		case Divid: //leftf(x)/rightf(x)
			return this.left.f(x)/this.right.f(x);
		case Error:
			throw new IllegalArgumentException("Error!");
		case Max:	//max(leftf(x) ,rightf(x))
			if(this.left.f(x)>this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case Min:	//min(leftf(x) ,rightf(x))
			if(this.left.f(x)<this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case None:	// leftf(x)
			return this.left.f(x);
		case Plus:	// leftf(x)+rightf(x)
			return this.left.f(x)+this.right.f(x);
		case Times:	// leftf(x)*rightf(x)
			return this.left.f(x)*this.right.f(x);
		}
		return 0;
	}
	/**
	 * 
	 * @return this method returns a string represents the logical value of our complex function's structure
	 */
	@Override
	public String toString() {
		switch (this.op){
		case Comp:
			return "comp("+this.left.toString()+","+this.right.toString()+")";
		case Divid:
			return "div("+this.left.toString()+","+this.right.toString()+")";
		case Error:
			throw new IllegalArgumentException("Error!");
		case Max:
			return "max("+this.left.toString()+","+this.right.toString()+")";
		case Min:
			return "min("+this.left.toString()+","+this.right.toString()+")";
		case None:
			return this.left.toString();
		case Plus:
			return "plus("+this.left.toString()+","+this.right.toString()+")";
		case Times:
			return "mul("+this.left.toString()+","+this.right.toString()+")";
		}
		return "";
	}
	/**
	 * this method builds a new function according to a given string
	 * @param s the string we build a function from. 
	 * @return polynom or a complex function depending on what the string represents
	 */
	@Override
	public function initFromString(String s) {
		s=s.replace(" ", "");
		if (s.contains("(")) {
			String [] sa; //splits the string to two substrings, the first is the main operation, the second is the rest.
			sa=s.split("\\(", 2);
			if(!sa[1].endsWith(")")) // the second substring must end with ')'
				throw new IllegalArgumentException("Invalid input:" + s+" you forgot ')'"); 
			String noB= sa[1].substring(0,sa[1].length()-1);
			int commaIndex= mainComma(noB);
			if(commaIndex==-1)
				throw new IllegalArgumentException("Invalid input:" + s+" main comma does not exist");// the comma that splits the string to left and right
			String f1=noB.substring(0,commaIndex); // string that represents the left function
			String f2=noB.substring(commaIndex+1,noB.length()); //// string that represents the right function
			return new ComplexFunction(this.initFromString(f1), this.initFromString(f2), sa[0]);
		}
		else {
			Polynom p=new Polynom(); // if there is no '(' the string represents a polynom 
			return p.initFromString(s);
		}
	}
	/**
	 * this method gets a string and returns the main comma index
	 * @param s the string we check
	 * @return main comma index
	 */
	private static int mainComma(String s) {
		int ans = -1;
		int c =0;
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if(ch=='(') {c++;}
			if(ch==')') {c--;}
			if(ch==',' && c==0) {
				ans = i;
			}
		}
		return ans;
	}
	/**
	 * this method checks whether the object and our complex function are visually similar. 
	 * if the object is not a function return false, else we check the range (-2,2) and in addition 1000 random integers.
	 * @param obj the object we compare to
	 * @return true if the values we checked are equals, false otherwise  
	 */
	public boolean equals(Object obj) {
		int failes=0;
		if (obj instanceof function) {
			for (double i=-2; i<=2; i=i+1000*Monom.EPSILON ) {
				if (Math.abs(((function) obj).f(i)-this.f(i))>Monom.EPSILON) {
					failes++;
					if(failes>2)
						return false;
				}
			}
			for (int i = 0; i < 1000; i++) {
				int d = (int) (Math.random()*10000*Math.pow(-1,i));
				if (obj instanceof Monom) {
					if (Math.abs(((function) obj).f(d)-this.f(d))>Monom.EPSILON) {
						failes++;
						if(failes>2)
							return false;
					}
				}
			}
			return true;
		}
		else { // if not instance of function
			return false;
		}
	}  
	/**
	 * @return deep copy of our the complex function 
	 */
	@Override
	public function copy() {	
		ComplexFunction cf=new ComplexFunction(new Monom("x")); // created only to activate initfromstring
		return (function) cf.initFromString(this.toString());
	}
	/**
	 * this method builds a new complex function 
	 * which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the plus.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void plus(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Plus;
	}
	/**
	 * this method builds a new complex function
	 * which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the times.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void mul(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Times;
	}
	/**
	 * this method builds a new complex function which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the divid.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void div(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Divid;
	}
	/**
	 * this method builds a new complex function which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the max.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void max(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Max;
	}
	/**
	 * this method builds a new complex function which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the min.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void min(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Min;
	}
	/**
	 * this method builds a new complex function which it's left side is our complex function’s old pointer,
	 * it's right side is f1's copy and it's operation is the comp.
	 * our complex function now points this new Complex function
	 * @param f1 the function to put on the right side 
	 */
	@Override
	public void comp(function f1) {
		function temp= f1.copy();
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = temp;
		this.op = Operation.Comp;
	}

}