import java.awt.*;


public class LocationChangingOval extends LocationChangingShape{
    private Dimension size;

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

        checkRep();
    }

    @Override
    /**
     * @return the blocking rectangle of this.oval
     */
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    @Override
    /**
     * @effects draw g
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
        g.drawOval((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        super.setColor(color);
        checkRep();
    }
}
