
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
		//insert to tree

		//insert to heap
		if(point.getY() == medianPoint.getY()) {
			if (point.getX() < medianPoint.getX()) {
				maxHeap.Insert(point);
			}
			else
				minHeap.Insert(point);
		}
		else
		{
			if(point.getY() < medianPoint.getY())
				maxHeap.Insert(point);
			else
				minHeap.Insert(point);
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
		return bTree.getLocation(XRight)-bTree.getLocation(XLeft)+1;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		if(bTree.getRoot() == null) return 0;
		BNode leftNode = bTree.getClosestNodeFromRight(XLeft);
		BNode rightNode = bTree.getClosestNodeFromLeft(XRight);
		if(leftNode.data.getX()>rightNode.data.getX()) return 0;
		int ySumOfPoints = bTree.getRoot().ySum;
		if(leftNode.left != null)
			ySumOfPoints = ySumOfPoints - leftNode.left.ySum;
		if(rightNode.right != null)
			ySumOfPoints = ySumOfPoints - rightNode.right.ySum;
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
		// TODO Auto-generated method stub
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

