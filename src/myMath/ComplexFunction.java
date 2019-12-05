package myMath;

public class ComplexFunction implements complex_function{

	private function left;
	private function right;
	private Operation op;

	/**
	 * @param left
	 * @param right
	 * @param op
	 */
	public ComplexFunction(function left, function right, Operation op) {
		this.left = left.copy();
		this.right = right.copy();
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
		this.op = op;
	}
	public ComplexFunction(Operation op, function left, function right) {
		this.left = left.copy();
		this.right = right.copy();
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
		this.op = op;
	}
	public ComplexFunction (function f)
	{
		this.left=f.copy();
		this.right=new Polynom("0");
		this.op=Operation.None;
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	public ComplexFunction(function left, function right, String s){
		this.left = left.copy();
		this.right = right.copy();
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	public ComplexFunction(String s, function left, function right){
		this.left = left.copy();
		this.right = right.copy();
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}
	public ComplexFunction(function left,String s,  function right){
		this.left = left.copy();
		this.right = right.copy();
		this.op=opRecognize(s);
		if(op==Operation.Error)
			throw new IllegalArgumentException("Error!");
	}

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
			throw new IllegalArgumentException("couldn't recognize the operation");
		}
	}
	public function getLeft() {
		return left;
	}
	public function getRight() {
		return right;
	}
	public Operation getOp() {
		return op;
	}
	@Override
	public double f(double x) {
		function f=(function)this;
		if (f instanceof Polynom) {
			Polynom p=(Polynom) f;
			return p.f(x);
		}
		if (f instanceof Monom) {
			Monom m=(Monom)f;
			return m.f(x);
		}	
		switch (this.op){
		case Comp:
			double rightAns=this.right.f(x);
			return this.left.f(rightAns);
		case Divid:
			return this.left.f(x)/this.right.f(x);
		case Error:
			throw new IllegalArgumentException("Error!");
		case Max:
			if(this.left.f(x)>this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case Min:
			if(this.left.f(x)<this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case None:
			return this.left.f(x);
		case Plus:
			return this.left.f(x)+this.right.f(x);
		case Times:
			return this.left.f(x)*this.right.f(x);
		}
		return 0;
	}
	@Override
	public String toString() {
		function f=(function)this;
		if (f instanceof Polynom) {
			return ((Polynom)f).toString();
		}
		if (f instanceof Monom) {
			return ((Monom)f).toString();
		}	
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
	@Override
	public function initFromString(String s) {
		s=s.replace(" ", "");
		if (s.contains("(")) {
			String [] sa;
			sa=s.split("\\(", 2);
			if(!sa[1].endsWith(")"))
				throw new IllegalArgumentException("Invalid input:" + s+" you forgot ')'");
			String noB= sa[1].substring(0,sa[1].length()-1);
			int commaIndex= mainComma(noB);
			if(commaIndex==-1)
				throw new IllegalArgumentException("Invalid input:" + s+" comma does not exist");
			String f1=noB.substring(0,commaIndex);
			String f2=noB.substring(commaIndex+1,noB.length());
			return new ComplexFunction(this.initFromString(f1), this.initFromString(f2), sa[0]);
		}
		else {
			Polynom p=new Polynom();
			return p.initFromString(s);
		}
	}
	public static int mainComma(String s) {
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
			for (int i = 0; i < 10; i++) {
				for (double j=Math.random() ;j<Math.random()+5; j+=10000*Monom.EPSILON ) {
					if (Math.abs(((function) obj).f(j)-this.f(j))>Monom.EPSILON) {
						failes++;
						if (failes>2)
							return false;}
				}
			}
			return true;
		}
		return false;
	}  
	//	public boolean tryequals(Object cf) {
	//		if (cf instanceof ComplexFunction)
	//			return this.equals((ComplexFunction)cf);
	//		return false;
	//	}
	//	public boolean tryequals(ComplexFunction cf) {//issue!!!!!!
	//		if (this.toString().contentEquals(cf.toString()))//same writing
	//				return true;
	//		if(this.op!=cf.op) 
	//			return false;
	//		else {
	//			boolean Ll=this.left.equals(cf.left);
	//			boolean Rr=this.right.equals(cf.right);
	//			boolean Lr=this.left.equals(cf.right);
	//			boolean Rl=this.right.equals(cf.left);
	//			boolean RrLl=Rr&&Ll;
	//			boolean RlLr=Rl&&Lr;
	//			switch (this.op) {
	//			case Comp:
	//				return RrLl;
	//			case Divid:
	//				return RrLl;
	//			case Error:
	//				return RrLl||RlLr;
	//			case Max:
	//				return RrLl||RlLr;
	//			case Min:
	//				return RrLl||RlLr;
	//			case None:
	//				return RrLl||RlLr;
	//			case Plus:
	//				return RrLl||RlLr;
	//			case Times:
	//				return RrLl||RlLr;
	//			}
	//			return false;
	//		}
	//	}
	@Override
	public function copy() {	
		return (function) new ComplexFunction(this.left.copy(), this.right.copy(), this.op);
	}
	@Override
	public void plus(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Plus;
	}
	@Override
	public void mul(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Times;
	}
	@Override
	public void div(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Divid;
	}
	@Override
	public void max(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Max;
	}
	@Override
	public void min(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Min;
	}
	@Override
	public void comp(function f1) {
		this.left= new ComplexFunction(this.left,this.right,this.op);
		this.right = f1.copy();
		this.op = Operation.Comp;
	}
	@Override
	public function left() {
		return this.left;
	}
	@Override
	public function right() {
		return this.right;
	}
}
