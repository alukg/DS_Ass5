/**
 * Created by shahar on 07/06/2016.
 * class to describe a node in a BST tree
 */

public class BNode {


    BNode left, right;
    BNode father;
    Point data;
    int size;
    int ySum;

    /**
     * constructor
     * left - left child of the node in the tree.
     * right - right child of the node in the tree.
     * data - contains the data of the node. the data is iPoint type.
     * father - contains a link to the node that is the father in the tree.
     * size - conatais the size of the subtree of the node (including itself)
     * ySum - conatains the Sum of the point y value
     *        of each node that is child of the node (including itself)
     */
    public BNode()
    {
        left = null;
        right = null;
        data = null;
        father = null;
        size = 0;
        ySum = 0;
    }

    /* Constructor */
    public BNode(Point p)
    {
        left = null;
        right = null;
        father = null;
        data = p;
        size = 0;
        ySum = 0;
    }

}
