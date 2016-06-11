/**
 * Created by guyal on 11/06/2016.
 */
public class MaxMinHeap
{
    private int[] Heap;
    private int size;
    private int maxSize;
    private String type;

    private static final int FRONT = 1;

    public MaxMinHeap(int[] sortedArr, String type)
    {
        this.type = type;
        this.maxSize = sortedArr.length *2;
        this.size = 0;
        Heap = new int[this.maxSize];
        if(type.equals("max"))
            Heap[0] = Integer.MAX_VALUE;
        else
            Heap[0] = Integer.MIN_VALUE;
    }

    private int parent(int index)
    {
        return index / 2;
    }

    private int leftChild(int index)
    {
        return (2 * index);
    }

    private int rightChild(int index)
    {
        return (2 * index) + 1;
    }

    private boolean isLeaf(int index)
    {
        if (index >=  (size / 2)  &&  index <= size)
        {
            return true;
        }
        return false;
    }

    private void swap(int first,int second)
    {
        int tmp;
        tmp = Heap[first];
        Heap[first] = Heap[second];
        Heap[second] = tmp;
    }

    private void heapify(int index)
    {
        if (!isLeaf(index))
        {
            if(type.equals("max")){
                if ( Heap[index] < Heap[leftChild(index)] || Heap[index] < Heap[rightChild(index)])
                {
                    if (Heap[leftChild(index)] > Heap[rightChild(index)])
                    {
                        swap(index, leftChild(index));
                        heapify(leftChild(index));
                    }
                    else
                    {
                        swap(index, rightChild(index));
                        heapify(rightChild(index));
                    }
                }
            }
            else{
                if ( Heap[index] > Heap[leftChild(index)] || Heap[index] > Heap[rightChild(index)])
                {
                    if (Heap[leftChild(index)] < Heap[rightChild(index)])
                    {
                        swap(index, leftChild(index));
                        heapify(leftChild(index));
                    }
                    else
                    {
                        swap(index, rightChild(index));
                        heapify(rightChild(index));
                    }
                }
            }
        }
    }

    public void insert(int element)
    {
        size++;
        Heap[size] = element;
        int current = size;

        if(type.equals("max")){
            while(Heap[current] > Heap[parent(current)])
            {
                swap(current,parent(current));
                current = parent(current);
            }
        }
        else{
            while(Heap[current] < Heap[parent(current)])
            {
                swap(current,parent(current));
                current = parent(current);
            }
        }
    }

    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i]
                    + " RIGHT CHILD :" + Heap[2 * i  + 1]);
            System.out.println();
        }
    }

    public int remove()
    {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size];
        size--;
        heapify(FRONT);
        return popped;
    }
}