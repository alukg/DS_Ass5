
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
			iPoint ipoint = new iPoint(points[i-1],maxHeap.heapSize);
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
		if(point.getY() == medianPoint.getY()) {
			if (point.getX() < medianPoint.getX()) {
				ipoint = new iPoint(point,maxHeap.heapSize);
				maxHeap.Insert(ipoint);
			}
			else
				ipoint = new iPoint(point,minHeap.heapSize);
				minHeap.Insert(ipoint);
		}
		else
		{
			if(point.getY() < medianPoint.getY()) {
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
			iPoint midPoint = new iPoint(medianPoint ,minHeap.heapSize);
			minHeap.Insert(midPoint);
			medianPoint = maxHeap.ExtractMax().point;
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
		int ySumOfPoints = bTree.getSumbyNode(rightNode.data.getX())-bTree.getSumbyNode(leftNode.data.getX())+ bTree.getNode(leftNode.data.getX()).data.getY();
		return (ySumOfPoints/numOfPointsInRange(XLeft,XRight));
	}

	@Override
	public void removeMedianPoint() {
		if(maxHeap.heapSize > minHeap.heapSize){
			medianPoint = maxHeap.ExtractMax().point;
		}
		else if(maxHeap.heapSize == 0){
			medianPoint = null;
		}
		else{
			medianPoint = minHeap.ExtractMax().point;
		}
	}

	@Override
	public Point[] getMedianPoints(int k) {
		Point[] medianPoints = new Point[k];
		medianPoints[0] = medianPoint;
		Heap maxHeap = new Heap(k, true);
		for (int i = 0; i < k / 2; i++) {

		}
		return null;
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

