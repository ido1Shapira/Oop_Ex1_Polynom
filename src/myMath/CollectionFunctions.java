package myMath;
import java.awt.Color;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class CollectionFunctions implements functions {
	private ArrayList<function> functionsList;

	/**
	 * Default constructor
	 */
	public CollectionFunctions() {
		this.functionsList = new ArrayList<function>();
	}

	@Override
	public int size() {
		return this.functionsList.size();
	}

	@Override
	public boolean isEmpty() {
		return this.functionsList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		if(o instanceof function) {
			return this.functionsList.contains((function) o);
		}
		return false;
	}

	@Override
	public Iterator<function> iterator() {
		return this.functionsList.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.functionsList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.functionsList.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return this.functionsList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return this.functionsList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.functionsList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return this.functionsList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.functionsList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.functionsList.retainAll(c);
	}

	@Override
	public void clear() {
		this.functionsList.clear();
	}

	@Override
	public void initFromFile(String pathFile) throws IOException {
		try {
			FileInputStream fstream = new FileInputStream(pathFile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			while ((strLine = br.readLine()) != null)   {
				ComplexFunction cf=new ComplexFunction(new Polynom("0"));
				function newf = cf.initFromString(strLine);
				functionsList.add(newf);
			}

		} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	public void paintFunction(function f,int width, int height, Range rx, Range ry, int resolution) {
		int n = 1000;
		double maxY = ry.get_max(), minY = ry.get_min();
		double maxX = rx.get_max(), minX = rx.get_min();
		double[] x = new double[n+1];
		double[] y = new double[n+1];
//		for (int i = 0; i <= n; i++) {
//			x[i] = i / n;
//			y[i] = 4*x[i];
//		}
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
		//		//////// x axis
		StdDraw.setPenColor(Color.black);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(minX, y[n/2], maxX, y[n/2]);
		//////// y axis
		StdDraw.line(x[n/2], minY, x[n/2], maxY);
		StdDraw.setPenColor(Color.red);
		StdDraw.setPenRadius(0.005);
		for (double i = minY; i <= maxY; i=i+5) {
			if(i!=0)
				StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
		}

		for (double i = minX; i <= maxX; i=i+2) {
			StdDraw.text( i+0.07 ,y[n/2]-0.07, Double.toString(i));
		}
		Random random = new Random();
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		StdDraw.setPenColor(r,g,b);
		StdDraw.setPenRadius(0.005);
		for (double i = minX; i < maxX; i+=0.005) {
			StdDraw.line(i, f.f(i), i+0.005, f.f(i+0.005));
		}
	}
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);
		for (Iterator<function> iterator = functionsList.iterator(); iterator.hasNext();) {
			function function = (function) iterator.next();
			paintFunction(function,width,height,rx,ry,resolution);
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
