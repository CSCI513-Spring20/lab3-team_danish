import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Garden extends Application {
    private AnchorPane root;
    private Scene scene;
    private Point2D clickPoint;
    private Flower flower;
    private FlowerBed flowerBed;
    private Point2D lastPosition;
    private boolean dragMode = false;
    private GardenObject currObject;
    private List<GardenObject> shapes = new ArrayList<GardenObject>();

    @Override
    public void start(Stage stage) {
        root = new AnchorPane();
        scene = new Scene(root, 800, 800);
        flower = new Flower(new Point2D(10, 10), Color.RED, 20, true);
        flowerBed = new FlowerBed(new Point2D (100, 100), 200, 150);
        shapes.add(flowerBed);
        shapes.add(flower);
        lastPosition = flower.getCurrentPosition();

        root.getChildren().add(flowerBed.getRect());
        root.getChildren().add(flower.getCircle());
        scene.setFill(Color.LIGHTGREEN);

        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        stage.setTitle("Garden");
        stage.setScene(scene);
        stage.show();

    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            clickPoint = new Point2D(event.getX(), event.getY());
            String eventName = event.getEventType().getName();

            if(!dragMode)
                currObject = getShape();

            switch(eventName) {
                case "MOUSE_DRAGGED":
                    if(lastPosition != null) {
                        double deltaX = clickPoint.getX() - lastPosition.getX();
                        double deltaY = clickPoint.getY() - lastPosition.getY();

                        if(currObject != null) {
                            dragMode = true;
                            currObject.moveRelative(deltaX, deltaY);
                        }
                    }
                    break;

                case "MOUSE_RELEASED":
                    if(currObject!=null && currObject instanceof Flower){
                        for(GardenObject container: shapes){
                            if (container instanceof FlowerBed && container.containsPoint(clickPoint)){
                                ((FlowerBed)container).addChild((Flower)currObject);
                                break;
                            }
                        }
                    }
                    else
                        currObject = null;
                    dragMode = false;
                    break;
            }
            lastPosition = clickPoint;
        }
    };

    private GardenObject getShape() {
        for(GardenObject myObject: shapes) {
            if(myObject.containsPoint(clickPoint))
                return myObject;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
