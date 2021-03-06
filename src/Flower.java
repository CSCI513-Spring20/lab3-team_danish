import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower implements GardenObject {
    private Point2D currentPosition;
    private Color color2D;
    private boolean moveable;
    private double radius;
    private Circle circle = new Circle();

    public Flower(Point2D position, Color color, double radius, boolean moveable) {
        this.currentPosition = position;
        this.color2D = color;
        this.moveable = moveable;
        this.radius = radius;
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        circle.setRadius(radius);
        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());
    }

    @Override
    public void move(double x, double y) {
        circle.setCenterX(circle.getCenterX() + x);
        circle.setCenterY(circle.getCenterY() + y);
    }

    @Override
    public boolean containsPoint(Point2D point) {
        if(circle.contains(point))
            return true;
        return false;
    }

    @Override
    public void moveRelative(double X, double Y) {
        circle.setCenterX(circle.getCenterX()+X);
        circle.setCenterY(circle.getCenterY()+Y);

    }

    public void removeStroke() {
        this.circle.setStroke(Color.GREEN);
    }

    public Circle getCircle() {
        return circle;
    }

    public Point2D getCurrentPosition() {
        return currentPosition;
    }
}
