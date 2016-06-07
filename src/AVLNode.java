/**
 * Created by shahar on 07/06/2016.
 */
public class AVLNode {

    AVLNode left, right;
    Point data;
    int height;
    int size;
    int ySum;

    /* Constructor */
    public AVLNode()
    {
        left = null;
        right = null;
        data = null;
        height = 0;
        size = 0;
        ySum = 0;
    }
    /* Constructor */
    public AVLNode(Point p)
    {
        left = null;
        right = null;
        data = p;
        height = 0;
        size = 0;
        ySum = 0;
    }
}
