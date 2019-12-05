package myMath;

public class Canvas {
	public int Width;
	public int Height;
	public int Resolution;
	public double[] Range_X = new double[2];
	public double[] Range_Y=  new double[2];
	/**
	 * @param width
	 * @param height
	 * @param resolution
	 * @param range_X
	 * @param range_Y
	 */
	public Canvas(int width, int height, int resolution, double[] range_X, double[] range_Y) {
		super();
		Width = width;
		Height = height;
		Resolution = resolution;
		Range_X = range_X;
		Range_Y = range_Y;
	}
	
	
}
