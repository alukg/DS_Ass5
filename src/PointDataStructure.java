import java.lang.reflect.Array;

public class PointDataStructure implements PDT {


	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint)
	{
		//sortArray();
		//CreateTree();
		//CreateHeap();
		//TODO
	}

	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeMedianPoint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getMedianPoints(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getAllPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO: add members, methods, etc.
	private boolean isArraysorted(Point arr[])
	{
		for (int i=0;i<arr.length-1;i++)
		{
			if(arr[i].getX() > arr[i+1].getX())
				return false;
		}
		return true;
	}
	private Point[] sortArray(Point arr[])
	{
		if(isArraysorted(arr))
			return arr;
		else
		{
			Point [] arr2 = new Point[arr.length];
			for (int i=0; i<arr.length; i++)
			{
				arr2[arr[i].getX()] = arr[i];
			}
			return arr2;
		}
	}
}

