import javafx.geometry.Point2D;

public interface GardenObject {
    public void move(double x, double y);
    public boolean containsPoint(Point2D point);
    public void removeStroke();
    public void moveRelative(double X, double Y);
}
