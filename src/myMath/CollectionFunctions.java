package myMath;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
		return this.size();
	}

	@Override
	public boolean isEmpty() {
		return this.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return this.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return this.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return this.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

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

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
