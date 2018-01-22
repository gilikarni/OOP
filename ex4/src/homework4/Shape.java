package homework4;

import java.awt.*;

/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color}.
 * Shapes are mutable.
 */
public abstract class Shape {

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
                "All fields must be initialized";
    }

    /**
     * @effects Initializes this with a a given location and color.
     */
    public Shape(Point location, Color color) {

        this.location = new Point(location);
        this.color = color;
        checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        return new Point(location);
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
}
