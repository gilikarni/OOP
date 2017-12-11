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
     * @requires location != null && color != null && size != null
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     * shape holds a oval with bounds the size of size.
     */
    LocationChangingOval(Point location, Color color, Dimension size) {
        super(location, color);

        this.size = new Dimension(size);
        checkRep();
    }

    @Override
    /**
     * @requires size != null
     * @modifies this
     * @effects Changes the size of oval according to the given dimensions
    */
    public void setSize(Dimension size) throws ImpossibleSizeException{
        if (size == null) {
            ImpossibleSizeException e = new ImpossibleSizeException();
            this.size = e.getCorrectSize();
            return;
        }

        this.size = (Dimension) size.clone();

        checkRep();
    }

    /**
     * @return the blocking rectangle of this.oval
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    /**
     * @requires g != null
     * @effects the oval to g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
        g.drawOval((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    /**
     * @requires color != null
     * @modifies this
     * @effects Sets color of this.
     */
    @Override
    public void setColor(Color color) {
        super.setColor(color);
        checkRep();
    }
}
