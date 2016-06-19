/**
 * class to describe a node in a BST tree
 */
public class BSNode {
    BSNode left, right;
    BSNode parent;
    Point data;
    int size;
    int ySum;

    /**
     * constructor
     * left - left child of the node in the tree.
     * right - right child of the node in the tree.
     * data - contains the data of the node. the data is IPoint type.
     * parent - contains a link to the node that is the parent in the tree.
     * size - contains the size of the subtree of the node (including itself)
     * ySum - contains the Sum of the point y value of each node that is child of the node (including itself)
     */
    public BSNode()
    {
        left = null;
        right = null;
        data = null;
        parent = null;
        size = 0;
        ySum = 0;
    }

    /**
     * Constructor with accepted data.
     * @param p the data to insert to the created node.
     */
    public BSNode(Point p)
    {
        left = null;
        right = null;
        parent = null;
        data = p;
        size = 0;
        ySum = 0;
    }

}
