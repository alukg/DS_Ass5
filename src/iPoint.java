/**
 * Created by shahar on 13/06/2016.
 */
public class iPoint {
    Point point;
    int index;

    public iPoint(Point point)
    {
        this(point,-1);
    }
    public iPoint(Point point, int index)
    {
        this.point = point;
        this.index = index;
    }
}
