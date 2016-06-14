
public class PointDataStructure implements PDT {

	private iPoint medianPoint;
	private BTree bTree;
	private Heap minHeap;
	private Heap maxHeap;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint)
	{
		Point[] sortedPoints = sortArray(points);
		bTree = new BTree(sortedPoints);
		maxHeap = new Heap(sortedPoints.length/2+1,true);
		minHeap = new Heap(sortedPoints.length/2+1,false);
		medianPoint = new iPoint (initialYMedianPoint,-1);
		for(int i=0;i<points.length/2;i++){
			iPoint ipoint = new iPoint(points[i],maxHeap.heapSize);
			maxHeap.Insert(ipoint);
		}
		for(int i=(points.length/2+2);i<=points.length;i++){
			iPoint ipoint = new iPoint(points[i-1],minHeap.heapSize);
			minHeap.Insert(ipoint);
		}
	}

	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		//insert to tree

		//insert to heap
		iPoint ipoint;
		if(point.getY() == medianPoint.point.getY()) {
			if (point.getX() < medianPoint.point.getX()) {
				ipoint = new iPoint(point,maxHeap.heapSize);
				maxHeap.Insert(ipoint);
			}
			else
				ipoint = new iPoint(point,minHeap.heapSize);
				minHeap.Insert(ipoint);
		}
		else
		{
			if(point.getY() < medianPoint.point.getY()) {
				ipoint = new iPoint(point, maxHeap.heapSize);
				maxHeap.Insert(ipoint);
			}
			else
			{
				ipoint = new iPoint(point,minHeap.heapSize);
				minHeap.Insert(ipoint);
			}
		}
		if (maxHeap.heapSize - minHeap.heapSize >1)
		{
			minHeap.Insert(medianPoint);
			medianPoint = maxHeap.ExtractMax();
		}
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		int numOfPoints = numOfPointsInRange(XLeft,XRight);
		if(numOfPoints == 0) return null;
		else{
			Point[] points = new Point[numOfPoints];
			return bTree.inorder(points,XLeft,XRight);
		}
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		if(bTree.getRoot() == null) return 0;
		BNode leftNode = bTree.getClosestNodeFromRight(XLeft);
		BNode rightNode = bTree.getClosestNodeFromLeft(XRight);
		if(leftNode == null || rightNode == null) return 0;
		if(leftNode.data.getX() > rightNode.data.getX()) return 0;
		return bTree.getLocationByNode(rightNode.data.getX())-bTree.getLocationByNode(leftNode.data.getX())+1;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		if(bTree.getRoot() == null) return 0;
		BNode leftNode = bTree.getClosestNodeFromRight(XLeft);
		BNode rightNode = bTree.getClosestNodeFromLeft(XRight);
		if(leftNode == null || rightNode == null) return 0;
		if(leftNode.data.getX()>rightNode.data.getX()) return 0;
		double ySumOfPoints = bTree.getSumbyNode(rightNode.data.getX())-bTree.getSumbyNode(leftNode.data.getX())+ bTree.getNode(leftNode.data.getX()).data.getY();
		return (ySumOfPoints/numOfPointsInRange(XLeft,XRight));
	}

	@Override
	public void removeMedianPoint() {
		if(maxHeap.heapSize > minHeap.heapSize){
			medianPoint = maxHeap.ExtractMax();
		}
		else if(maxHeap.heapSize == 0){
			medianPoint = null;
		}
		else{
			medianPoint = minHeap.ExtractMax();
		}
	}

	@Override
	public Point[] getMedianPoints(int k) {

		int i=0;
		Point[] medianPoints = new Point[k];
		Heap kMaxHeap = new Heap(k/2, true);
		medianPoints[0] = medianPoint.point;
		kMaxHeap.Insert(maxHeap.heapArr[i]);
		for(int j=1;j<k/2+1; j++)
		{
			iPoint p = kMaxHeap.ExtractMax();
			i=p.index;
			medianPoints[j] = p.point;
			kMaxHeap.Insert(maxHeap.heapArr[2 * (i + 1) - 1]);
			kMaxHeap.Insert(maxHeap.heapArr[2 * (i + 1)]);
		}
		i=0;
		Heap kMinHeap = new Heap(k/2, false);
		kMinHeap.Insert(minHeap.heapArr[i]);
		for(int j=k/2+1; j<k;j++)
		{
			iPoint p = kMinHeap.ExtractMax();
			i=p.index;
			medianPoints[j] = p.point;
			kMinHeap.Insert(minHeap.heapArr[2 * (i + 1) - 1]);
			kMinHeap.Insert(minHeap.heapArr[2 * (i + 1)]);
		}
		return medianPoints;
	}

	@Override
	public Point[] getAllPoints() {
		Point [] points = new Point[maxHeap.heapSize + minHeap.heapSize +1];
		BNode root = bTree.getRoot();
		points = bTree.inorder(points,bTree.getMin(root).data.getX(),bTree.getMax(root).data.getX());
		return points;
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