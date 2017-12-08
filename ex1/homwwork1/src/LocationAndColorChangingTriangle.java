import java.awt.*;

public class LocationAndColorChangingTriangle extends LocationAndColorChangingShape {
    private Dimension bounds = null;
    private Graphics triangle = null;
    private final int numberOfPointsInPoly = 3;
    private int[] xArray;
    private int[] yArray;

    /**
     * @param location
     * @param dim
     * @modifies xArray, yArray, triangle
     * @effects Creates a new triangle with the new dimensions
     */
    private void setTriangle(Point location, Dimension dim) {

        xArray = new int[]{location.x, location.x, location.x + dim.width};
        yArray = new int[]{location.y, location.y + dim.height, location.y};
        triangle.fillPolygon(xArray, yArray, numberOfPointsInPoly);
    }

    /**
     * @param location
     * @param color
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationAndColorChangingTriangle(Point location, Color color, Dimension bounds) {
        super(location, color);

        this.bounds = bounds;
        setTriangle(location, bounds);
    }

    @Override
    /**
     * @param dimension
     * @effects Changes the size of oval according to the given dimensions
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        if(null == dimension) {
            throw new ImpossibleSizeException();
        }

        bounds = dimension;
        setTriangle(getLocation(), bounds);

        super.checkRep();
    }

    @Override
    /**
     * @return the blocking rectangle of this.triangle
     */
    public Rectangle getBounds() {
        Rectangle clipBounds = triangle.getClipBounds();

        return clipBounds;
    }

    @Override
    /**
     * @param color
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        super.setColor(color);

        triangle.setColor(color);
    }

    @Override
    /**
     * @param g
     * @effects draw g
     */
    public void draw(Graphics g) {
        g.drawPolygon(xArray, yArray, numberOfPointsInPoly);
    }
}
