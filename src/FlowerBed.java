import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FlowerBed implements GardenObject {
    Point2D topLeft;
    int height;
    int width;
    Color rectangleColor;
    Rectangle rect = new Rectangle();
    List<GardenObject> myGardenObjects = new ArrayList<GardenObject>();

    public FlowerBed(Point2D topLeft, int height, int width) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
        rect.setFill(Color.GREEN);
        rect.setHeight(height);
        rect.setWidth(width);
        rect.setX(topLeft.getX());
        rect.setY(topLeft.getY());
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);
    }

    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void move(double x, double y) {
        rect.setX(rect.getX() + x);
        rect.setY(rect.getY() + y);
    }
}
