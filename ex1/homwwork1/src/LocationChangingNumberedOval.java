import java.awt.*;

public class LocationChangingNumberedOval extends LocationChangingOval {
    private static Integer counter = 1;
    private Integer myNumber = 0;

    /**
     * @param location
     * @param color
     * @param dimension
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingNumberedOval(Point location, Color color, Dimension dimension) {
        super(location, color, dimension);

        myNumber = counter;
        counter++;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = getLocation().x / 2;
        int y = getLocation().y / 2;
        g.drawString(myNumber.toString(), x, y);
    }

    public static void clearCounter() {
        counter = 0;
    }
}
