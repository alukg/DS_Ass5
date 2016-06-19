/**
 * Created by shahar on 13/06/2016.
 * Object class that contains a point and index.
 */

public class IPoint {
    Point point;
    int index;

    /**
     * Constructor
     * @param point data to insert.
     * get point and put -1 in the index
     */
    public IPoint(Point point)
    {
        this(point,-1);
    }

    /**
     * constructor with index
     * @param point data to insert.
     * @param index index of the point.
     */
    public IPoint(Point point, int index)
    {
        this.point = point;
        this.index = index;
    }
}
