/**
 * Created by shahar on 07/06/2016.
 */
public class AVLTree {
    private AVLNode root;

    /* Constructor */
    public AVLTree(Point arr[])
    {
        root = sortedArrayToBST(arr, 0, arr.length-1);;
    }

    private AVLNode sortedArrayToBST(Point arr[], int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        AVLNode node = new AVLNode(arr[mid]);
        node.left = sortedArrayToBST(arr, start, mid-1);
        node.right = sortedArrayToBST(arr, mid+1, end);
        addSize(root);
        // insertSumValues():
        //insertize();
        return node;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Function to insert data */
    public void insert(Point p)
    {
        root = insert(p, root);
    }
    /* Function to get height of node */
    private int height(AVLNode t )
    {
        if (t==null)
            return -1;
        else
            return t.height;
    }
    /* Function to max of left/right node */
    private int max(int leftheight, int rightheight)
    {
        if(leftheight > rightheight)
            return leftheight;
        else
            return rightheight;
    }
    /* Function to insert data recursively */
    private AVLNode insert(Point p, AVLNode t)
    {
        if (t == null)
            t = new AVLNode(p);
        else if (p.getX() < t.data.getX())
        {
            t.left = insert( p, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( p.getX() < t.left.data.getX() )
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( p.getX() > t.data.getX() )
        {
            t.right = insert( p, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( p.getX() > t.right.data.getX())
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        else
            ;  // Duplicate; do nothing
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    /* Rotate binary tree node with left child */
    private AVLNode rotateWithLeftChild(AVLNode k2)
    {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private AVLNode rotateWithRightChild(AVLNode k1)
    {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child */
    private AVLNode doubleWithLeftChild(AVLNode k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */
    private AVLNode doubleWithRightChild(AVLNode k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(AVLNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(int val)
    {
        return search(root, val);
    }
    private boolean search(AVLNode r, int val)
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
    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(AVLNode r)
    {

        if (r != null)
        {
            inorder(r.left);
            System.out.println(r.data.getX() +" " + r.data.getY() +" "+ r.ySum + " " + r.size);
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(AVLNode r)
    {
        if (r != null)
        {
            System.out.print(r.data +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(AVLNode r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data +" ");
        }
    }
    private void addSize(AVLNode node)
    {
        int counter=0, sum=0;
        if(node.left !=null) {
            addSize(node.left);
            counter += node.left.size;
            sum +=node.left.ySum;
        }
        if(node.right !=null) {
            addSize(node.right);
            counter += node.right.size;
            sum +=node.right.ySum;
        }
        node.size = counter +1;
        node.ySum = sum + node.data.getY();
    }


    private AVLNode location(AVLNode node, int num) {
        if (node.left.size +1 == num)
            return node;
        else
            if(node.left.size >= num)
                return location(node.left, num);
            else
                return location(node.right, num- node.left.size -1);

    }
}
