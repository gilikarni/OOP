import java.awt.*;

public class AngleChangingSector extends Shape implements Animatable {

    private int startAngle;
    private int arcAngle;
    private Dimension size = null;
    private boolean directionUp = true;
    private final static int maxDegree = 360;
    private final static int minDegree = 0;

    /*
    Abstraction Function:
    A AngleChangingSector an Animatable Shape. The sector animation open the arc up until 359 degrees,
     than closes it down to 0 degrees

    Representation invariant for every AngleChangingSector l:
    Representation invariant for Shape  && size != null &&
    The start degree and arcDegree must be in the range of [0,360)
    */

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     *
     */
    @Override
    protected void checkRep() {
        super.checkRep();
        assert (startAngle >= minDegree) && (startAngle < maxDegree) :
                "An angle must be in the range [" + minDegree + "," + maxDegree + ")";
        assert (arcAngle >= minDegree) && (arcAngle < maxDegree) :
                "An angle must be in the range [" + minDegree + "," + maxDegree + ")";
    }

    /**
     * @return Convert angle to be in the range [0, 360)
     */
    private static int convertToLegalAngle(int angle) {
        int legalAngle = angle % maxDegree;
        legalAngle += maxDegree;
        legalAngle %= maxDegree;

        return legalAngle;
    }

    /**
     * @requires size != null
     * @effects Initializes this with a a given location, color, startAngle, arcAngle, size.
     */
    public AngleChangingSector(Point location, Color color, int startAngle, int arcAngle, Dimension size) throws NullPointerException {
        super(location, color);

        if (size == null) {
            throw new NullPointerException();
        }

        this.startAngle = convertToLegalAngle(startAngle);
        this.arcAngle = convertToLegalAngle(arcAngle);
        this.size = new Dimension(size);
        checkRep();
    }

    /**
     * @modifies arcAngle, arc
     * @effects Change the size of the angle.
     *          First move arcAngle from it initial value to 359 degrees, than move it back to 0, and again.
     */
    @Override
    public void step(Rectangle bound) {
        if (arcAngle == minDegree) {
            directionUp = true;
        } else if (arcAngle == maxDegree - 1) {
            directionUp = false;
        }

        if (directionUp) {
            arcAngle++;
        } else {
            arcAngle--;
        }

        checkRep();
    }

    /**
     * @requires size != null
     * @modifies size, arc
     * @effects change the bounding triangle of the arc to be of the of the dimensions of size
     */
    @Override
    public void setSize(Dimension size) {
        if (size == null) {
            ImpossibleSizeException e = new ImpossibleSizeException();
            this.size = e.getCorrectSize();
            return;
        }

        this.size = new Dimension(size);
        checkRep();
    }

    /**
     * @return the bounding rectangle of the oval that bounds the arc.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    /**
     * @requires g != null
     * @effects draw to screen the arc that is saved in this class.
     */
    @Override
    public void draw(Graphics g) {
        if (g == null) {
            throw new NullPointerException();
        }
        g.setColor(getColor());
        g.fillArc((int)getLocation().getX(), (int)getLocation().getY(), (int)size.getWidth(), (int)size.getHeight(),
                startAngle, arcAngle);
        g.drawArc((int)getLocation().getX(), (int)getLocation().getY(), (int)size.getWidth(), (int)size.getHeight(),
                startAngle, arcAngle);
    }

    /**
     * @return A deep copy of this
     */
    @Override
    public Object clone() {
        AngleChangingSector angleChangingSector = (AngleChangingSector) super.clone();
        angleChangingSector.setSize(this.size);

        return angleChangingSector;
    }
}
