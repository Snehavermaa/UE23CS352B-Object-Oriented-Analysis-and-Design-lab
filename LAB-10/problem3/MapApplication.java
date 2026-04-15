// Problem 3: Flyweight Pattern
import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface Marker {
    void display(int x, int y);
}

// Concrete Flyweight: shared intrinsic state
class ConcreteMarker implements Marker {
    private String icon;
    private String color;

    public ConcreteMarker(String icon, String color) {
        this.icon = icon;
        this.color = color;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying marker with icon: " + icon + ", color: " + color + " at (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class MarkerFactory {
    private static Map<String, Marker> markers = new HashMap<>();

    public static Marker getMarker(String icon, String color) {
        String key = icon + "_" + color;
        if (!markers.containsKey(key)) {
            markers.put(key, new ConcreteMarker(icon, color));
            System.out.println("Creating new marker: " + key);
        } else {
            System.out.println("Reusing existing marker: " + key);
        }
        return markers.get(key);
    }
}

// Client class that holds extrinsic state
class MapMarker {
    private Marker flyweight;
    private int x, y;

    public MapMarker(String icon, String color, int x, int y) {
        this.flyweight = MarkerFactory.getMarker(icon, color);
        this.x = x;
        this.y = y;
    }

    public void display() {
        flyweight.display(x, y);
    }
}

// Client code
public class MapApplication {
    public static void main(String[] args) {
        MapMarker marker1 = new MapMarker("restaurant", "red", 10, 20);
        MapMarker marker2 = new MapMarker("hospital", "blue", 30, 40);
        MapMarker marker3 = new MapMarker("restaurant", "red", 50, 60); // Same intrinsic state

        marker1.display();
        marker2.display();
        marker3.display();
    }
}