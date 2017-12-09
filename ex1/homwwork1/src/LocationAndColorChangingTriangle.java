import java.awt.*;

public class LocationAndColorChangingTriangle extends LocationAndColorChangingShape {
    private Dimension size = null;
    private Polygon triangle = null;
    private final int numberOfPointsInPoly = 3;
    private int[] xArray;
    private int[] yArray;

        /*
    Abstraction Function:
    A LocationAndColorChangingTriangle l is located at location with the color color. l can move in the speed l.super.velocity.x
    horizontally and in l.super.velocity.y vertically. The color of l changes every time the velocity changes.

    Representation invariant for every LocationAndColorChangingTriangle l:
    Representation invariant for LocationAndColorChangingShape &&
    triangle != null && size != null
    */

    /**
     * @requires location != null && dim != null
     * @modifies xArray, yArray, triangle
     * @effects Creates a new triangle with the new dimensions
     */
    private void setTriangle(Point location, Dimension dim) {
        xArray = new int[]{location.x, location.x, location.x + dim.width};
        yArray = new int[]{location.y, location.y + dim.height, location.y};

        triangle = new Polygon(xArray, yArray, numberOfPointsInPoly);
    }

    /**
     * @requires location != null && dim != null
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationAndColorChangingTriangle(Point location, Color color, Dimension size) {
        super(location, color);

        if (size == null) {
            throw new NullPointerException();
        }

        this.size = new Dimension(size);
        setTriangle(location, size);

        checkRep();
    }

    @Override
    /**
     * @requires dimension != null
     * @effects Changes the size of oval according to the given dimensions
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        if(dimension == null) {
            throw new ImpossibleSizeException();
        }

        size = dimension;
        setTriangle(getLocation(), size);

        checkRep();
    }

    @Override
    /**
     * @return the blocking rectangle of this.triangle
     */
    public Rectangle getBounds() {
        return new Rectangle(triangle.getBounds());
    }

    @Override
    /**
     * @requires color != null
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new NullPointerException();
        }

        super.setColor(color);

        checkRep();
    }

    @Override
    /**
     * @requires g != null
     * @effects draw the triangle to g
     */
    public void draw(Graphics g) {
        if (g == null) {
            throw new NullPointerException();
        }

        g.setColor(getColor());
        g.fillPolygon(xArray, yArray, numberOfPointsInPoly);
        g.drawPolygon(xArray, yArray, numberOfPointsInPoly);
    }

    /**
     * @return A deep copy of this
     */
    @Override
    public Object clone() {
        LocationAndColorChangingTriangle locationAndColorChangingTriangle = (LocationAndColorChangingTriangle) super.clone();
        locationAndColorChangingTriangle.size = (Dimension) this.size.clone();

        return triangle;
    }

    /**
     * @effects Assert that the representation invariant hold
     * @throws AssertionError if the the representation invariant breaks
     */
    @Override
    protected void checkRep() {
        super.checkRep();

        assert size != null:
                "The size must be different than null";

        assert triangle != null:
                "Triangle must be different than null";
    }

    /**
     * @requires bound != null
     * @modifies this
     * @effects Set the new location of the triangle
     */
    @Override
    public void step(Rectangle bound) {
        super.step(bound);

        setTriangle(getLocation(), size);
    }
}
