import java.awt.*;

public class LocationChangingOval extends LocationChangingShape{

    private Dimension size;
    private Graphics oval = null;

     /*
    Abstraction Function:
    A LocationChangingOval l is a LocationChangingShape.
    1 is viewed as an coloured color oval with the bounding rectangle of size size,
    located at locations

    Representation invariant for every LocationChangingOval l:
    Representation invariant for LocationChangingShape &&
    size != null && oval != null
    */

    @Override
    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */ protected void checkRep() {
        super.checkRep();
        assert size != null: "size of LocationChangingOval is null";
        assert oval != null: "oval of LocationChangingOval is null";
    }

    /**
     * @param location
     * @param color
     * @param size
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     * shape holds a oval with bounds the size of size.
     */
    LocationChangingOval(Point location, Color color, Dimension size) {
        super(location, color);

        if (size == null) {
            throw new NullPointerException();
        }
        this.size = new Dimension(size);
        this.oval.fillOval(location.x, location.y, size.width, size.height);
        checkRep();
    }

    @Override
    /**
     * @effects Changes the size of oval according to the given dimensions
    */
    public void setSize(Dimension size) throws ImpossibleSizeException{
        if (size == null) {
            throw new ImpossibleSizeException();
        }

        this.size = (Dimension) size.clone();
        this.oval.fillOval(super.getLocation().x, super.getLocation().y, size.width, size.height);

        checkRep();
    }

    @Override
    /**
     * @return the blocking rectangle of this.oval
     */
    public Rectangle getBounds() {
        Rectangle rectangle = null;
        oval.getClipBounds(rectangle);

        return rectangle;
    }

    @Override
    /**
     * @effects draw g
     */
    public void draw(Graphics g) {
        g.drawOval(super.getLocation().x, super.getLocation().y, size.width, size.height);
    }

    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        super.setColor(color);

        oval.setColor(color);
        checkRep();
    }
}
