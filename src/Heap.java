/**
 * Created by shahar on 11/06/2016.
 * heap data structure that contains iPoints elements.
 *
 */

public class Heap {

    IPoint[] heapArr;
    int heapSize;
    boolean isMax;
    boolean changeIndex; //decide if to change the index of the points when changing place.

    /**
     * constructor
     * @param arrLength - describe the length of the heap array
     * @param isMax - boolean value to determine if maximum heap or minimum heap
     * @param changeIndex - boolean value to determine if the heap change the IPoint indexes.
     */
    public Heap(int arrLength, boolean isMax, boolean changeIndex) {
        heapArr = new IPoint[arrLength * 2]; //set max size of the heap.
        heapSize = 0;
        this.isMax = isMax; //decide which heap - max or min.
        this.changeIndex = changeIndex;
    }

    public Heap(IPoint[] arr, int size, boolean isMax, boolean changeIndex) {
        heapArr = arr;
        heapSize = size;
        this.isMax = isMax; //decide which heap - max or min.
        this.changeIndex = changeIndex;
        for(int i=size/2+1;i>=0;i--){
            DownHeapify(i);
        }
    }

    /**
     * insert function - to insert an IPoint into the heap
     * @param data - IPoint parameter to insert
     */
    public void Insert(IPoint data) {
        if(changeIndex)
            data.index = heapSize;
        heapArr[heapSize] = data;
        UpHeapify(heapSize);
        heapSize++;
    }

    /**
     * the function extract the value from the heap and heapify it.
     * @return the maximum value for maximum heap and minimum value for minimum heap
     */
    public IPoint ExtractMax() {
        if(heapSize==0)
            return null;
        else {
            IPoint p = heapArr[0];
            heapArr[0] = heapArr[heapSize -1]; //set the last node to be the first.
            if(changeIndex)
                heapArr[0].index=0;
            heapSize--;
            DownHeapify(0); //fix the heap from the root down.
            return p;
        }
    }

    /**
     * fix the heap down side
     * @param index - describe which index in the heap may not be ok
     */
    public void DownHeapify(int index) {
        if (isMax) {
            if (2 * (index + 1) >= heapSize) {
                if(2 * (index + 1) - 1 < heapSize)
                    if (getMax(index, 2 * (index + 1) - 1) == 2 * (index + 1) - 1) {
                        swapiPoints(index, 2 * (index + 1) - 1);
                }
            } else {
                int maxChildIndex = getMax(2 * (index + 1) - 1, 2 * (index + 1));
                if (getMax(index, maxChildIndex) == maxChildIndex) {
                    swapiPoints(index, maxChildIndex);
                    if (2 * (index + 1) - 1 < heapSize)
                        DownHeapify(maxChildIndex);
                }
            }
        } else {
            if (2 * (index + 1) >= heapSize) {
                if(2 * (index + 1) - 1 < heapSize)
                    if (getMin(index, 2 * (index + 1) - 1) == 2 * (index + 1) - 1) {
                        swapiPoints(index, 2 * (index + 1) - 1);
                }
            } else {
                int minChildIndex = getMin(2 * (index + 1) - 1, 2 * (index + 1));
                if (getMin(index, minChildIndex) == minChildIndex) {
                    swapiPoints(index, minChildIndex);
                    if (minChildIndex < heapSize)
                        DownHeapify(minChildIndex);
                }
            }
        }
    }

    /**
     * fix the heap upside
     * @param index - describe which index in the heap may not be ok
     */
    public void UpHeapify(int index) {
        int parentIndex = (int) (Math.ceil((double) index / 2)) - 1;
        if (isMax) {
            if (parentIndex >= 0) {
                if (getMin(index, parentIndex) == parentIndex) {
                    swapiPoints(index, parentIndex);
                    UpHeapify(parentIndex);
                }
            }
        } else {
            if (parentIndex >= 0) {
                if (getMax(index, parentIndex) == parentIndex) {
                    swapiPoints(index, parentIndex);
                    UpHeapify(parentIndex);
                }
            }
        }
    }

    /**
     * switch between two points in the heap
     * @param x index for point X in the heap
     * @param y index for point Y in the heap
     */
    private void swapiPoints(int x, int y) {
        if(changeIndex){ //swap the index of the points
            int tempIndex = heapArr[x].index;
            heapArr[x].index = heapArr[y].index;
            heapArr[y].index = tempIndex;
        } //swap the place of the points.
        IPoint temp = heapArr[x];
        heapArr[x] = heapArr[y];
        heapArr[y] = temp;
    }

    /**
     * determine which point is higher - first by y values then by x values.
     * @param p1Index index for point p1 in the heap
     * @param p2Index index for point p2 in the heap
     * @return the higher point index
     */
    private int getMax(int p1Index, int p2Index) {
        if (heapArr[p1Index].point.getY() != heapArr[p2Index].point.getY())
            if (heapArr[p1Index].point.getY() == Math.max(heapArr[p1Index].point.getY(), heapArr[p2Index].point.getY()))
                return p1Index;
            else
                return p2Index;
        else if (heapArr[p1Index].point.getX() != heapArr[p2Index].point.getX())
            if (heapArr[p1Index].point.getX() == Math.max(heapArr[p1Index].point.getX(), heapArr[p2Index].point.getX()))
                return p1Index;
        return p2Index;

    }

    /**
     * determine which point is lower - first by y values then by x values.
     * @param p1Index index for point p1 in the heap
     * @param p2Index index for point p2 in the heap
     * @return the lower point index
     */
    private int getMin(int p1Index, int p2Index) {
        if (heapArr[p1Index].point.getY() != heapArr[p2Index].point.getY())
            if (heapArr[p1Index].point.getY() == Math.min(heapArr[p1Index].point.getY(), heapArr[p2Index].point.getY()))
                return p1Index;
            else
                return p2Index;
        else if (heapArr[p1Index].point.getX() != heapArr[p2Index].point.getX())
            if (heapArr[p1Index].point.getX() == Math.min(heapArr[p1Index].point.getX(), heapArr[p2Index].point.getX()))
                return p1Index;
        return p2Index;
    }
}
