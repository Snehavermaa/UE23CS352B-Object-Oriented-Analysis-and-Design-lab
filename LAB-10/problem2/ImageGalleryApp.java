// Problem 2: Proxy Pattern
// Image interface
interface Image {
    void loadImage();
    void displayImage();
}

// Real Image class that performs heavy loading
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImage();
    }

    @Override
    public void loadImage() {
        System.out.println("Loading image: " + filename + " (heavy operation)");
    }

    @Override
    public void displayImage() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy Image class that lazy loads and caches
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    private static java.util.Map<String, RealImage> cache = new java.util.HashMap<>();

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void loadImage() {
        if (realImage == null) {
            if (cache.containsKey(filename)) {
                realImage = cache.get(filename);
                System.out.println("Image loaded from cache: " + filename);
            } else {
                realImage = new RealImage(filename);
                cache.put(filename, realImage);
            }
        }
    }

    @Override
    public void displayImage() {
        loadImage(); // Lazy loading
        realImage.displayImage();
    }
}

// Client code
public class ImageGalleryApp {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // First display - loads
        image1.displayImage();
        image2.displayImage();

        // Second display - uses cache
        image1.displayImage();
        image2.displayImage();
    }
}