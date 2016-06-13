import java.awt.*;

/**
 * Created by shahar on 07/06/2016.
 */
public class BTree {
    private BNode root;
    private static int counter = 0;

    /**
     * Constructor.
     * @param arr array of values to insert the new tree.
     */
    public BTree(Point arr[])
    {
        this.root = sortedArrayToTree(arr, 0, arr.length-1, null);
        insertSizeAndSum(root);
    }

    private BNode sortedArrayToTree(Point arr[], int start, int end, BNode father) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        BNode node = new BNode(arr[mid]);
        node.father = father;
        node.left = sortedArrayToTree(arr, start, mid-1, node);
        node.right = sortedArrayToTree(arr, mid+1, end, node);
        return node;
    }

    /* Function to insert data */
    public void insert(Point p)
    {
        if(root == null){
            root = new BNode(p);
            root.size = 1;
            root.ySum = p.getY();
            root.father = null;
        }
        else{
            insert(p, root);
        }
    }

    /* Function to insert data recursively */
    private void insert(Point p, BNode bNode)
    {
        bNode.size++;
        bNode.ySum += p.getY();
        if (p.getX() < bNode.data.getX())
        {
            if(bNode.left == null)
            {
                BNode temp =new BNode(p);
                temp.size = 1;
                temp.ySum = p.getY();
                temp.father = bNode;
                bNode.left = temp;
            }
            else
                insert( p, bNode.left );
        }
        else
        {
            if(bNode.right == null) {
                BNode temp =new BNode(p);
                temp.size = 1;
                temp.ySum = p.getY();
                temp.father = bNode;
                bNode.right = temp;
            }
            else
                insert( p, bNode.right );
        }
    }

    /**
     * Functions to search if element is exists.
     * @param val to search for.
     * @return if the value exists.
     */
    public boolean search(int val)
    {
        return search(root, val);
    }

    private boolean search(BNode r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.data.getX();
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    /**
     * Function to get the node with some value.
     * @param val value to search.
     * @return the BNode with the value.
     */
    public BNode getNode(int val)
    {
        if(root == null)
            return null;
        return getNode(root, val);
    }


    //private Point getClosestPointfromLeft(int xLeft)
    //{
     /*   int nodeVal = bNode.data.getX();
        if (val < nodeVal)
            return getNode(bNode.left, val);
        else if (val > nodeVal)
            return getNode(bNode.right, val);
        else
            return bNode;*/
    //}

    private BNode getNode(BNode bNode, int val)
    {
        int nodeVal = bNode.data.getX();
        if (val < nodeVal)
            return getNode(bNode.left, val);
        else if (val > nodeVal)
            return getNode(bNode.right, val);
        else
            return bNode;
    }

    public void remove (int val){
        BNode bNode = getNode(val);
        if (bNode == null) return;
        else
            remove(getNode(val).data,root);
    }

    private void remove (Point point, BNode bNode)
    {
        if (point.getX() < bNode.data.getX()){
            bNode.size--;
            bNode.ySum = bNode.ySum - point.getY();
            remove (point, bNode.left);
        }
        else if (point.getX() > bNode.data.getX()){
            bNode.size--;
            bNode.ySum = bNode.ySum - point.getY();
            remove (point, bNode.right);
        }
        else {
            if (bNode.left != null && bNode.right != null)
            {
                if(bNode.right.size > bNode.left.size){
                    BNode minFromRight = getMin(bNode.right);
                    bNode.data = minFromRight.data;
                    bNode.size--;
                    bNode.ySum = bNode.ySum - minFromRight.data.getY();
                    remove (minFromRight.data, bNode.right);
                }
                else{
                    BNode maxFromLeft = getMax(bNode.left);
                    bNode.data = maxFromLeft.data;
                    bNode.size--;
                    bNode.ySum = bNode.ySum - maxFromLeft.data.getY();
                    remove (maxFromLeft.data, bNode.left);
                }
            }
            else if(bNode.left != null) {
                if(bNode == root)
                    root = bNode.left;
                if(bNode.father.left == bNode)
                    bNode.father.left = bNode.left;
                else
                    bNode.father.right = bNode.left;
                bNode.left.father = bNode.father;
                bNode.left = null;
            }
            else if(bNode.right != null) {
                if(bNode == root)
                    root = bNode.right;
                else if(bNode.father.left == bNode)
                    bNode.father.left = bNode.right;
                else
                    bNode.father.right = bNode.right;
                bNode.right.father = bNode.father;
                bNode.right = null;
            }
            else {
                if(bNode == root)
                    root = null;
                else if(bNode.father.left == bNode)
                    bNode.father.left = null;
                else
                    bNode.father.right = null;
            }
        }
    }

    private BNode location(BNode node, int num) {
        if (node.left.size +1 == num)
            return node;
        else
            if(node.left.size >= num)
                return location(node.left, num);
            else
                return location(node.right, num- node.left.size -1);

    }

    /**
     * Print Inorder traversal
     */
    public Point[] inorder(Point[] arr, int XLeft, int XRight)
    {
        inorder(arr, root,XLeft,XRight);
        counter = 0;
        return arr;
    }

    private void inorder(Point[] arr, BNode bNode, int XLeft, int XRight)
    {
        if (bNode != null)
        {
            if(XLeft < bNode.data.getX())
                inorder(arr, bNode.left, XLeft, XRight);
            if(bNode.data.getX() >= XLeft && bNode.data.getX() <= XRight)
                arr[counter] = bNode.data;
            if(XRight > bNode.data.getX())
                inorder(arr, bNode.right, XLeft, XRight);
        }
    }

    private void insertSizeAndSum(BNode node)
    {
        int counter=0, sum=0;
        if(node.left !=null) {
            insertSizeAndSum(node.left);
            counter += node.left.size;
            sum +=node.left.ySum;
        }
        if(node.right !=null) {
            insertSizeAndSum(node.right);
            counter += node.right.size;
            sum +=node.right.ySum;
        }
        node.size = counter +1;
        node.ySum = sum + node.data.getY();
    }

    public BNode getMax(BNode bNode){
        if(bNode.right == null){
            return bNode;
        }
        else{
            return getMax(bNode.right);
        }
    }

    public BNode getMin(BNode bNode){
        if(bNode.left == null){
            return bNode;
        }
        else{
            return getMin(bNode.left);
        }
    }
}
