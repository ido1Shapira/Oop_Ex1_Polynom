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
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		StringBuilder SB = new StringBuilder();
		
		return SB.substring(0);
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
