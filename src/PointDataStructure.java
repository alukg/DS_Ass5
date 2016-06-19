
public class PointDataStructure implements PDT {

	private IPoint medianPoint;
	private BSTree bsTree;
	private Heap minHeap;
	private Heap maxHeap;

	/**
	 * Constructor.
	 * @param points array of points.
	 * @param initialYMedianPoint the median point in the array by the Y parameter.
     */
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint)
	{
		Point[] sortedPoints = sortArray(points); //sort the array of points by the X value of the points.
		IPoint[] minPoints = new IPoint[sortedPoints.length * 2];
		IPoint[] maxPoints = new IPoint[sortedPoints.length * 2];
		bsTree = new BSTree(sortedPoints);
		int minCounter=0;
		int maxCounter=0;
		for(int i=0;i<sortedPoints.length;i++){ //insert the points from the array to the heaps by it Y value.
			if(sortedPoints[i].getY() < initialYMedianPoint.getY()){ //if the Y smaller from the median.
				IPoint ipoint = new IPoint(sortedPoints[i],maxCounter);
				maxPoints[maxCounter] = ipoint;
				maxCounter++;
			}
			else if(sortedPoints[i].getY() > initialYMedianPoint.getY()){ //if the Y bigger from the median.
				IPoint ipoint = new IPoint(sortedPoints[i],minCounter);
				minPoints[minCounter] = ipoint;
				minCounter++;
			}
			else{ //if the Ys equal.
				if(sortedPoints[i].getX() < initialYMedianPoint.getX()){ //if the X smaller from the median.
					IPoint ipoint = new IPoint(sortedPoints[i],maxCounter);
					maxPoints[maxCounter] = ipoint;
					maxCounter++;
				}
				else if(sortedPoints[i].getX() > initialYMedianPoint.getX()) { //if the X bigger from the median.
					IPoint ipoint = new IPoint(sortedPoints[i],minCounter);
					minPoints[minCounter] = ipoint;
					minCounter++;
				}
				else { //if it's the same point.
					IPoint ipoint = new IPoint(sortedPoints[i], -1);
					medianPoint = ipoint; //set as median point.
				}
			}
		}
		maxHeap = new Heap(maxPoints,maxCounter,true,true); //create max heap with changing indexes, for all the points that small from the madian point.
		minHeap = new Heap(minPoints,minCounter,false,true); //create min heap with changing indexes, for all the points that small from the madian point.
	}

	/**
	 * Add new point for the data structure.
	 * @param point the point to add.
     */
	public void addPoint(Point point) {
		this.bsTree.insert(point); //insert the point to the tree.
		IPoint ipoint;
		if(point.getY() == medianPoint.point.getY()) { //if the Ys equal check by the Xs.
			if (point.getX() < medianPoint.point.getX()) {
				ipoint = new IPoint(point,maxHeap.heapSize);
				maxHeap.Insert(ipoint);
			}
			else
				ipoint = new IPoint(point,minHeap.heapSize);
				minHeap.Insert(ipoint);
		}
		else
		{
			if(point.getY() < medianPoint.point.getY()) {
				ipoint = new IPoint(point, maxHeap.heapSize);
				maxHeap.Insert(ipoint);
			}
			else
			{
				ipoint = new IPoint(point,minHeap.heapSize);
				minHeap.Insert(ipoint);
			}
		}
		if (maxHeap.heapSize - minHeap.heapSize >1) //if the maxHeap bigger by more than one (that means by 2), balance the median point.
		{
			minHeap.Insert(medianPoint);
			medianPoint = maxHeap.ExtractMax();
		}
		if (minHeap.heapSize - maxHeap.heapSize  == 1) //if the maxHeap bigger by more than one (that means by 2), balance the median point.
		{
			maxHeap.Insert(medianPoint);
			medianPoint = minHeap.ExtractMax();
		}
	}

	/**
	 * Get all the points in some range.
	 * @param XLeft left domain.
	 * @param XRight right domain.
     * @return array of the points in the range.
     */
	public Point[] getPointsInRange(int XLeft, int XRight) {
		int numOfPoints = numOfPointsInRange(XLeft,XRight);
		if(numOfPoints == 0) return (new Point[0]); //if there is no points in that range.
		else{
			Point[] points = new Point[numOfPoints];
			return bsTree.inorder(points,XLeft,XRight); //get inorder of those points in the domain recursively.
		}
	}

	/**
	 * Get the number of points in some range of X values.
	 * @param XLeft left domain.
	 * @param XRight right domain.
     * @return the number of points on the range.
     */
	public int numOfPointsInRange(int XLeft, int XRight) {
		if(bsTree.getRoot() == null) return 0;
		//do it for the closest nodes that exists for that values.
		BSNode leftNode = bsTree.getClosestNodeFromRight(XLeft);
		BSNode rightNode = bsTree.getClosestNodeFromLeft(XRight);
		if(leftNode == null || rightNode == null) return 0;
		if(leftNode.data.getX() > rightNode.data.getX()) return 0;
		return bsTree.getLocationByNode(rightNode.data.getX())-bsTree.getLocationByNode(leftNode.data.getX())+1;
	}

	/**
	 * Get the average of Y values in some range of X values.
	 * @param XLeft left domain.
	 * @param XRight right domain.
     * @return the average value in the range.
     */
	public double averageHeightInRange(int XLeft, int XRight) {
		if(bsTree.getRoot() == null) return 0;
		int numOfPoints = numOfPointsInRange(XLeft,XRight);
		if(numOfPoints == 0) return 0; //if there is no points in the range.
		//do it for the closest nodes that exists for that values.
		BSNode leftNode = bsTree.getClosestNodeFromRight(XLeft);
		BSNode rightNode = bsTree.getClosestNodeFromLeft(XRight);
		double ySumOfPoints = bsTree.getSumbyNode(rightNode.data.getX())-bsTree.getSumbyNode(leftNode.data.getX())+ bsTree.getNode(leftNode.data.getX()).data.getY();
		return (ySumOfPoints/numOfPoints);
	}

	/**
	 * Remove the median point from the data structure.
	 */
	public void removeMedianPoint() {
		bsTree.remove(medianPoint.point.getX()); //remove the point with that x value from the tree.
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

	/**
	 * Get the median point and all the k-1 points that closest to it.
	 * @param k number of points to return, include median.
	 * @return array of the k's median points
     */
	public Point[] getMedianPoints(int k) {
		Point[] medianPoints = new Point[k];
		medianPoints[0] = medianPoint.point; //set the median point first.
		int i=0;
		Heap kMaxHeap = new Heap(k, true, false); //new max heap without changing indexes.
		kMaxHeap.Insert(maxHeap.heapArr[i]);
		for(int j=1;j<k/2+1; j++)
		{
			IPoint p = kMaxHeap.ExtractMax(); //get the next point.
			i=p.index;
			medianPoints[j] = p.point;
			if ((2 * (i + 1)-1) < maxHeap.heapSize)
				kMaxHeap.Insert(maxHeap.heapArr[2 * (i + 1) - 1]);
			if ((2 * (i + 1)) < maxHeap.heapSize)
				kMaxHeap.Insert(maxHeap.heapArr[2 * (i + 1)]);
		}
		i=0;
		Heap kMinHeap = new Heap(k, false, false); //new min heap without changing indexes.
		kMinHeap.Insert(minHeap.heapArr[i]);
		for(int j=k/2+1; j<k;j++)
		{
			IPoint p = kMinHeap.ExtractMax(); //get the next point.
			i=p.index;
			medianPoints[j] = p.point;
			if ((2 * (i + 1)-1) < minHeap.heapSize)
				kMinHeap.Insert(minHeap.heapArr[2 * (i + 1) - 1]);
			if ((2 * (i + 1)) < minHeap.heapSize)
				kMinHeap.Insert(minHeap.heapArr[2 * (i + 1)]);
		}
		return medianPoints;
	}

	/**
	 * Get all the points in the data structure.
	 * @return array of all the points.
     */
	public Point[] getAllPoints() {
		Point [] points = new Point[maxHeap.heapSize + minHeap.heapSize +1];
		BSNode root = bsTree.getRoot();
		points = bsTree.inorder(points,bsTree.getMin(root).data.getX(),bsTree.getMax(root).data.getX()); //get all the points by there inorder.
		return points;
	}

	/**
	 * Check if array of points is sorted.
	 * @param arr the array to check.
	 * @return of the array is sorted.
     */
	private boolean isArraySorted(Point arr[])
	{
		for (int i=0;i<arr.length-1;i++)
		{
			if(arr[i].getX() > arr[i+1].getX())
				return false;
		}
		return true;
	}

	/**
	 * Bucket sort.
	 * @param arr of points to sort.
	 * @return the sorted array.
     */
	private Point[] sortArray(Point arr[])
	{
		if(isArraySorted(arr))
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