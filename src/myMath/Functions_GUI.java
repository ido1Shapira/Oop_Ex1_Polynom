package myMath;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Functions_GUI implements functions {
	private ArrayList<function> functionsList;

	/**
	 * Default constructor
	 */
	public Functions_GUI() {
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
	public void initFromFile(String pathFile) {
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
			br.close();

		} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void saveToFile(String file) throws IOException {		
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			StringBuilder sb = new StringBuilder();

			for (Iterator<function> iterator = functionsList.iterator(); iterator.hasNext();) {
				function f = (function) iterator.next();
				sb.append(f.toString() + "\n");

			}
			pw.write(sb.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public Color paint(function f, Range rx, Range ry, int res) {	
		double maxY = ry.get_max(), minY = ry.get_min();
		double maxX = rx.get_max(), minX = rx.get_min();
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY); 
		StdDraw.setPenColor(Color.black);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(minX, 0, maxX, 0);
		StdDraw.line(0, minY, 0, maxY);		
		double[] x=new double[res+1];
		double[] y=new double[res+1];
		double m=(maxX-minX)/(res);
		double n=minX;

		for (int i = 0; i < res; i++) {
			x[i]=m*i+n;
			y[i]=f.f(x[i]);	
		}
		for (double i=minY; i<maxY; i++)
		{
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.0005);
			StdDraw.line(minX,i,maxX,i);

		}
		for (double i=minX; i<maxX; i++)
		{
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.0005);
			StdDraw.line(i,minY,i,maxY);
		}
		StdDraw.setPenColor(Color.red); StdDraw.setPenRadius(0.005);
		for (int i = (int) minY; i <= maxY; i=i+1) {
			if(i!=0)
				StdDraw.text((minX/(minX-maxX))-0.07, i+0.07, Integer.toString(i));
		}
		for (int i = (int) minX; i <= maxX; i=i+1) {
			if(i!=0)
				StdDraw.text(i+0.07 ,minY/(minY-maxY)-0.07, Integer.toString(i));
		}
		Random random = new Random();
		int r = random.nextInt(256); int g = random.nextInt(256); int b = random.nextInt(256);
		StdDraw.setPenColor(r,g,b); StdDraw.setPenRadius(0.005);
		for (int i = 0; i < res-1; i++) {
			StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
		}

		return new Color(r,g,b);
	}
	public void drawFunctions() {
		this.drawFunctions(400, 400, new Range(-10,10), new Range (-5,15), 200);
	}
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		int i=0;
		if(resolution<=0)
			throw new IllegalArgumentException();
		StdDraw.setCanvasSize(width, height);
		for (Iterator<function> iterator = functionsList.iterator(); iterator.hasNext();) {
			function function = (function) iterator.next();
			Color colorFunc = paint(function,rx,ry,resolution);
			System.out.println(i+") "+colorFunc.toString() + "   f(x)=" + function.toString());
			i++;
		}
	}
	@Override
	public void drawFunctions(String json_file) {
		try 
		{
			Gson gson = new Gson();
			FileReader reader = new FileReader(json_file);
			Canvas c = gson.fromJson(reader,Canvas.class);	
			if(c.Range_X[0]> c.Range_X[1] || c.Range_Y[0]> c.Range_Y[1])
				throw new IllegalArgumentException();
			this.drawFunctions(c.Width,c.Height,new Range(c.Range_X[0],c.Range_X[1]),new Range(c.Range_Y[0],c.Range_Y[1]),c.Resolution);
		} 
		catch (FileNotFoundException e) {
			this.drawFunctions("GUI_params.json");
		}
		catch (IllegalArgumentException e) {
			this.drawFunctions("GUI_params.json");
		}
		catch (JsonSyntaxException e) {
			this.drawFunctions("GUI_params.json");

		}

	}

	public function get(int i) {
		return this.functionsList.get(i);
	}

}
