/**
 * Created by shahar on 13/06/2016.
 * Object class that contains a point and index.
 */

public class iPoint {
    Point point;
    int index;

    /**
     * Constructor
     * @param point
     * get point and put -1 in the index
     */
    public iPoint(Point point)
    {
        this(point,-1);
    }

    /**
     * constructor
     * @param point
     * @param index
     */
    public iPoint(Point point, int index)
    {
        this.point = point;
        this.index = index;
    }
}
