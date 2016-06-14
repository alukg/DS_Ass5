/**
 * Created by shahar on 11/06/2016.
 */
public class Heap {
    iPoint[] heapArr;
    int heapSize;
    boolean isMax;
    boolean changeIndex;

    //Constructor
    public Heap(int arrLength, boolean isMax, boolean changeIndex) {
        heapArr = new iPoint[arrLength * 2];
        heapSize = 0;
        this.isMax = isMax;
        this.changeIndex = changeIndex;
    }

    public void printArray() {
        for (int i = 0; i < heapSize; i++)
            System.out.print(heapArr[i] + " ");
    }

    public void Insert(iPoint data) {
        if(changeIndex)
            data.index = heapSize;
        heapArr[heapSize] = data;
        UpHeapify(heapSize);
        heapSize++;
    }

    public iPoint Max() {
        return heapArr[0];
    }

    public iPoint ExtractMax() {
        if(heapSize==0)
            throw new RuntimeException("Heap is Empty");
        else {
            iPoint p = heapArr[0];
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

    private void swapiPoints(int x, int y) {
        if(changeIndex){
            int tempIndex = heapArr[x].index;
            heapArr[x].index = heapArr[y].index;
            heapArr[y].index = tempIndex;
        }
        iPoint temp = heapArr[x];
        heapArr[x] = heapArr[y];
        heapArr[y] = temp;
    }

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
