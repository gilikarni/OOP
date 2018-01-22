package homework4;

import java.awt.*;

/**
 * Panel is a rectangular Shape that changes colors according to ColorGenerator
 */
public class Panel extends Shape{
    private final Dimension size;

    /** Abstraction Function:
     * Panel p is a rectangular Shape. p is also an observer of ColoGenerator that changes its color according to
     * updates from ColorGenerator.
     *
     * Representation Invariant:
     * Representation Invariant of Shape and
     * size is not null
     */

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */
    protected void checkRep() {
        super.checkRep();
        assert size != null:
            "All fields must be initialized";
    }

    public Panel(Point location, Color color, Dimension size){
        super(location, color);
        this.size = new Dimension(size);
        checkRep();
    }

    /**
     * Updates the color by an observable
     * @requires arg is a Color
     * @modifies this
     * @effects sets color according to arg
     *
     */
    public void update(Color arg) {
        setColor(arg);
        checkRep();
    }

    /**
     * @return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
        g.drawRect((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
    }
}
