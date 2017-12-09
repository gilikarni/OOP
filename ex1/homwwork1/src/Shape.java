import java.awt.*;

/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

    private Point location;
    private Color color;


    /*
    Abstraction Function:
    A Shape s is located at location with the color color.

    Representation invariant for every Shape s:
    location != null && color != null.
    */

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */

    protected void checkRep() {
        assert location != null && color != null :
                "All feilds must be initialized";
    }

    /**
     * @effects Initializes this with a a given location and color.
     * @param location
     * @param color
     */
    public Shape(Point location, Color color) {
        if (location == null || color == null) {
            throw new NullPointerException();
        }

        this.location = new Point(location);
        this.color = color;
        checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        Rectangle boundingRectangle = this.getBounds();
        Point topLeft =  boundingRectangle.getLocation();
        return topLeft;
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     *          returns location after call has completed.
     */
    public void setLocation(Point location) {
        this.location = (Point)location.clone();
        checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
    public abstract void setSize(Dimension dimension)
        throws ImpossibleSizeException;


    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();


    /**
     * @return true if the given point lies inside the bounding rectangle of
     *         this and false otherwise.
     */
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }


    /**
     * @return color of this.
     */
    public Color getColor() {
        return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        this.color = color;
        checkRep();
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        Shape newShape = null;
        try {
            newShape = (Shape)super.clone(); // shallow copy
        } catch (CloneNotSupportedException e) {
            /* The exception will never be thrown by super.clone(), but the catch
            prevents us from having to declare it in the signature of this.clone(). */
            assert false: "Got inside the CloneNotSupportedException exception of Shape.clone()";
        }
        newShape.location = (Point)this.location.clone();
        return newShape;
    }
}
