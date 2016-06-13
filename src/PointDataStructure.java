import java.lang.reflect.Array;

public class PointDataStructure implements PDT {

	private Point medianPoint;
	private BTree bTree;
	private Heap minHeap;
	private Heap maxHeap;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint)
	{
		Point[] sortedPoints = sortArray(points);
		bTree = new BTree(sortedPoints);
		maxHeap = new Heap(sortedPoints.length/2+1,false);
		minHeap = new Heap(sortedPoints.length/2+1,true);
		medianPoint = initialYMedianPoint;
		for(int i=1;i<points.length/2+1;i++){
			maxHeap.Insert(points[i-1]);
		}
		for(int i=(points.length/2+2);i<=points.length;i++){
			minHeap.Insert(points[i-1]);
		}
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
		if(maxHeap.heapSize > minHeap.heapSize){
			minHeap.
		}
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

