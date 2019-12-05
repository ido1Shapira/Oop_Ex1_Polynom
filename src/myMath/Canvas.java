package myMath;

public class Canvas {
	public int Width;
	public int Height;
	public int Resolution;
	public Range Range_X;
	public Range Range_Y;
	/**
	 * @param width
	 * @param height
	 * @param resolution
	 * @param range_X
	 * @param range_Y
	 */
	public Canvas(int width, int height, int resolution, Range range_X, Range range_Y) {
		Width = width;
		Height = height;
		Resolution = resolution;
		Range_X = range_X;
		Range_Y = range_Y;
	}
	
	
}
