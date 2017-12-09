import java.awt.*;

public class AngleChangingSector extends Shape implements Animatable {

    private int startAngle;
    private int arcAngle;
    Graphics arc = null;
    private Dimension bound = null;
    private boolean directionUp = true;
    private final static int maxDegree = 360;
    private final static int minDegree = 0;

    /*
    Abstraction Function:
    A AngleChangingSector a is located at location with the color color. The sector grows to 356 degrees, than it gets
    smaller to 0 degrees

    Representation invariant for every LocationChangingShape l:
    location != null && color != null && arc != null && bound != null &&
    The start degree and arcDegree mist be in the range of [0,360)
    */

    @Override
    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */ protected void checkRep() {
        super.checkRep();

        assert (startAngle >= minDegree) && (startAngle < maxDegree) :
                "An angle must be in the range [" + minDegree + "," + maxDegree + ")";

        assert (arcAngle >= minDegree) && (arcAngle < maxDegree) :
                "An angle must be in the range [" + minDegree + "," + maxDegree + ")";
    }

    /**
     * @param angle
     * @modifies angle
     * @return Convert angle to be in the range [0, 360)
     */
    private static int convertToLegalAngle(int angle) {
        int legalAngle = angle % maxDegree;
        legalAngle += maxDegree;
        legalAngle %= maxDegree;

        return legalAngle;
    }

    /**
     * @param location
     * @param color
     * @param startAngle
     * @param arcAngle
     * @param dimension
     * @effects Initializes this with a a given location, color, startAngle, arcAngle, dimension.
     */
    public AngleChangingSector(Point location, Color color, int startAngle, int arcAngle, Dimension dimension) throws NullPointerException {
        super(location, color);

        if (dimension == null) {
            throw new NullPointerException();
        }

        this.startAngle = convertToLegalAngle(startAngle);
        this.arcAngle = convertToLegalAngle(arcAngle);
        this.bound = new Dimension(dimension);

        arc.fillArc(location.x, location.y, dimension.width, dimension.height, startAngle, arcAngle);
    }

    /**
     * @param bound
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

        arc.fillArc(getLocation().x, getLocation().y, this.bound.width, this.bound.height, startAngle, arcAngle);
        checkRep();
    }

    /**
     * @param dimension
     * @throws ImpossibleSizeException
     * @modifies bound, arc
     * @effects change the bounding triangle of the arc to be of the of the dimensions of dimension
     */
    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        if (dimension == null) {
            throw new ImpossibleSizeException();
        }

        this.bound = dimension;

        arc.fillArc(getLocation().x, getLocation().y, this.bound.width, this.bound.height, startAngle, arcAngle);
        checkRep();
    }

    /**
     * @return the bounding rectangle of the oval that bounds the arc.
     */
    @Override
    public Rectangle getBounds() {
        return arc.getClipBounds();
    }

    /**
     * @param g
     * @effects draw to screen the arc that is saved in this class.
     */
    @Override
    public void draw(Graphics g) {
        g.drawArc(getLocation().x, getLocation().y, bound.width, bound.height, startAngle, arcAngle);
    }

    /**
     * @return A deep copy of this
     */
    @Override
    public Object clone() {
        AngleChangingSector angleChangingSector = (AngleChangingSector) super.clone();
        angleChangingSector.bound = (Dimension) this.bound.clone();

        return angleChangingSector;
    }
}
