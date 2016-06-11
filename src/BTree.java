/**
 * Created by shahar on 07/06/2016.
 */
public class BTree {
    private BNode root;

    /**
     * Constructor.
     * @param arr array of values to insert the new tree.
     */
    public BTree(Point arr[])
    {
        this.root = sortedArrayToTree(arr, 0, arr.length-1);
        insertSizeAndSum(root);
    }

    private BNode sortedArrayToTree(Point arr[], int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        BNode node = new BNode(arr[mid]);
        node.left = sortedArrayToTree(arr, start, mid-1);
        node.right = sortedArrayToTree(arr, mid+1, end);
        return node;
    }

    /* Function to insert data */
    public void insert(Point p)
    {
        if(root == null){
            root = new BNode(p);
        }
        else{
            insert(p, root);
        }
    }

    /* Function to insert data recursively */
    private void insert(Point p, BNode bNode)
    {
        if (p.getX() < bNode.data.getX())
        {
            bNode.size++;
            if(bNode.left == null)
            {
                BNode temp =new BNode(p);
                temp.size =1;
                temp.ySum = p.getY();
                bNode.left = temp;
            }
            else
                insert( p, bNode.left );
                bNode.ySum +=p.getY();
        }
        else if( p.getX() > bNode.data.getX() )
        {
            bNode.size++;
            if(bNode.right == null) {
                BNode temp =new BNode(p);
                temp.size =1;
                temp.ySum = p.getY();
                bNode.right = temp;
            }
            else
                insert( p, bNode.right );
                bNode.ySum +=p.getY();
        }
        else
            ;// Duplicate; do nothing

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
        return getNode(root, val);
    }

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

    /**
     * Print Inorder traversal
     */
    public void inorder()
    {
        inorder(root);
    }

    private void inorder(BNode bNode)
    {
        if (bNode != null)
        {
            inorder(bNode.left);
            System.out.println(bNode.data.getX() +" " + bNode.size+ " " + bNode.ySum);
            inorder(bNode.right);
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

    private BNode location(BNode node, int num) {
        if (node.left.size +1 == num)
            return node;
        else
            if(node.left.size >= num)
                return location(node.left, num);
            else
                return location(node.right, num- node.left.size -1);

    }
}
