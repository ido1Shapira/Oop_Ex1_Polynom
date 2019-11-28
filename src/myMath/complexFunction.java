package myMath;

public class complexFunction implements complex_function{

	private function left;
	private function right;
	private Operation op;

	/**
	 * @param left
	 * @param right
	 * @param op
	 */
	
	
	public complexFunction(function left, function right, Operation op) {
		this.left = left;
		this.right = right;
		this.op = op;
	}
	public complexFunction(function left, function right, String s){
		this.left = left;
		this.right = right;
		this.op=opRecognize(s);
	}
	public complexFunction(String s, function left, function right){
		this.left = left;
		this.right = right;
		this.op=opRecognize(s);
	}
	public complexFunction(function left,String s,  function right){
		this.left = left;
		this.right = right;
		this.op=opRecognize(s);
	}

	private Operation opRecognize (String s) {
		s=s.toLowerCase();
		System.out.println(s);
		switch(s) {
		case "plus":
			return Operation.Plus;
		case "comp":
			return Operation.Comp;
		case "divid":
			return Operation.Divid;
		case "error":
			return Operation.Error;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "none":	
			return Operation.None;
		case "times":
			return Operation.Times;
		default:
			System.out.println("couldn't recognize the operation");
			return Operation.Error;
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
			break;//throw exception
		case Max:
			if(this.left.f(x)>this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case Min:
			if(this.left.f(x)<this.right.f(x)) return this.left.f(x);
			return this.right.f(x);
		case None:
			break;
		case Plus:
			return this.left.f(x)+this.right.f(x);
		case Times:
			return this.left.f(x)*this.right.f(x);
		}
		return 0;
	}
	@Override
	public String toString() {
		StringBuilder SB = new StringBuilder();
		function f=(function)this;
		if (f instanceof Polynom) {
			Polynom p=(Polynom) f;
			return p.toString();
		}
		if (f instanceof Monom) {
			Monom m=(Monom)f;
			return m.toString();
		}	
		switch (this.op){
		case Comp:
			return "("+this.left.toString()+"("+this.right.toString()+"))";
		case Divid:
			return "("+this.left.toString()+") / ("+this.right.toString()+")";
		case Error:
			break;//throw exception
		case Max:
			return "(max("+this.left.toString()+" , "+this.right.toString()+"))";
		case Min:
			return "( min("+this.left.toString()+" , "+this.right.toString()+") )";
		case None:
			break;
		case Plus:
			return "("+this.left.toString()+") + ("+this.right.toString()+")";
		case Times:
			return "("+this.left.toString()+") / ("+this.right.toString()+")";
		}
		return "";
	}
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		return null;
	}
	@Override
	public void plus(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
		this.op = Operation.Plus;
	}
	@Override
	public void mul(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
		this.op = Operation.Times;
	}
	@Override
	public void div(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
		this.op = Operation.Divid;
	}
	@Override
	public void max(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
		this.op = Operation.Max;
	}
	@Override
	public void min(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
		this.op = Operation.Min;
	}
	@Override
	public void comp(function f1) {
		this.left= new complexFunction(this.left,this.right,this.op);
		this.right = f1;
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
