package myMath;
/*
 * this class represent a parameters of GOI window such as width,height,resolution,range of x and y.
 */
public class Canvas {
	public int Width;
	public int Height;
	public int Resolution;
	public double[] Range_X = new double[2];
	public double[] Range_Y=  new double[2];
	/**
	 * Canvas constructor
	 * @param width
	 * @param height
	 * @param resolution The number of points for drawing a function
	 * @param range_X The X axis length
	 * @param range_Y The Y axis length
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
