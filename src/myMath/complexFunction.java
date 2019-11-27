package myMath;

public class complexFunction implements function{

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
	public void setLeft(complexFunction left) {
		this.left = left;
	}
	public function getRight() {
		return right;
	}
	public void setRight(complexFunction right) {
		this.right = right;
	}
	public Operation getOp() {
		return op;
	}
	public void setOp(Operation op) {
		this.op = op;
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
		// TODO Auto-generated method stub
		return null;
	}

}
