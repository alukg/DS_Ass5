/**
 * Created by shahar on 11/06/2016.
 */
public class Heap {
    Point[] heapArr;
    int heapSize;
    boolean isMax;

    //Constructor
    public Heap(Point[] p, boolean isMax) {
        heapArr = new Point[p.length * 2];
        heapSize = 0;
        this.isMax = isMax;
        for (int i = 0; i < p.length; i++)
            Insert(p[i]);
    }

    public void printArray() {
        for (int i = 0; i < heapSize; i++)
            System.out.print(heapArr[i] + " ");
    }

    public void Insert(Point data) {
        heapArr[heapSize] = data;
        UpHeapify(heapSize);
        heapSize++;
    }

    public Point Max() {
        return heapArr[0];
    }

    public Point ExtractMax() {
        if(heapSize==0)
            throw new RuntimeException("Heap is Empty");
        else {
            Point p = heapArr[0];
            heapArr[0] = heapArr[heapSize - 1];
            heapSize--;
            DownHeapify(0);
            return p;
        }
    }

    public void DownHeapify(int index) {
        if (isMax) {
            if (2 * (index + 1) >= heapSize) {
                if(2 * (index + 1) - 1 < heapSize)
                    if (getMax(index, 2 * (index + 1) - 1) == 2 * (index + 1) - 1) {
                        swapPoints(index, 2 * (index + 1) - 1);
                }
            } else {
                int maxChildIndex = getMax(2 * (index + 1) - 1, 2 * (index + 1));
                if (getMax(index, maxChildIndex) == maxChildIndex) {
                    swapPoints(index, maxChildIndex);
                    if (2 * (index + 1) - 1 < heapSize)
                        DownHeapify(maxChildIndex);
                }
            }
        } else {
            if (2 * (index + 1) >= heapSize) {
                if(2 * (index + 1) - 1 < heapSize)
                    if (getMin(index, 2 * (index + 1) - 1) == 2 * (index + 1) - 1) {
                        swapPoints(index, 2 * (index + 1) - 1);
                }
            } else {
                int minChildIndex = getMin(2 * (index + 1) - 1, 2 * (index + 1));
                if (getMin(index, minChildIndex) == minChildIndex) {
                    swapPoints(index, minChildIndex);
                    if (minChildIndex < heapSize)
                        DownHeapify(minChildIndex);
                }
            }
        }
    }

    public void UpHeapify(int index) {
        int parentIndex = (int) (Math.ceil((double) index / 2)) - 1;
        if (isMax) {
            if (parentIndex >= 0) {
                if (getMin(index, parentIndex) == parentIndex) {
                    swapPoints(index, parentIndex);
                    UpHeapify(parentIndex);
                }
            }
        } else {
            if (parentIndex >= 0) {
                if (getMax(index, parentIndex) == parentIndex) {
                    swapPoints(index, parentIndex);
                    UpHeapify(parentIndex);
                }
            }
        }
    }

    private void swapPoints(int x, int y) {
        Point temp = heapArr[x];
        heapArr[x] = heapArr[y];
        heapArr[y] = temp;
    }

    private int getMax(int p1Index, int p2Index) {
        if (heapArr[p1Index].getY() != heapArr[p2Index].getY())
            if (heapArr[p1Index].getY() == Math.max(heapArr[p1Index].getY(), heapArr[p2Index].getY()))
                return p1Index;
            else
                return p2Index;
        else if (heapArr[p1Index].getX() != heapArr[p2Index].getX())
            if (heapArr[p1Index].getX() == Math.max(heapArr[p1Index].getX(), heapArr[p2Index].getX()))
                return p1Index;
        return p2Index;

    }

    private int getMin(int p1Index, int p2Index) {
        if (heapArr[p1Index].getY() != heapArr[p2Index].getY())
            if (heapArr[p1Index].getY() == Math.min(heapArr[p1Index].getY(), heapArr[p2Index].getY()))
                return p1Index;
            else
                return p2Index;
        else if (heapArr[p1Index].getX() != heapArr[p2Index].getX())
            if (heapArr[p1Index].getX() == Math.min(heapArr[p1Index].getX(), heapArr[p2Index].getX()))
                return p1Index;
        return p2Index;
    }
}
