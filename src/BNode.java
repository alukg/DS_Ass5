/**
 * Created by shahar on 07/06/2016.
 */
public class BNode {

    BNode left, right;
    BNode father;
    Point data;
    int size;
    int ySum;

    /* Constructor */
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
