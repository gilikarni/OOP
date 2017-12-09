import com.sun.javaws.exceptions.InvalidArgumentException;
import org.jetbrains.annotations.Contract;

import java.awt.*;

public class AngleChangingSector extends Shape implements Animatable {

    private int startAngle;
    private int arcAngle;
    Graphics arc = null;
    private Dimension bound = null;
    private boolean directionUp = true;
    private final static int maxDegree = 360;
    private final static int minDegree = 0;

    /**
     * @param angle
     * @return true iff angle is in [0,360]
     */
    @Contract(pure = true)
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
        this.bound = dimension;

        arc.fillArc(location.x, location.y, dimension.width, dimension.height, startAngle, arcAngle);

        if (arcAngle == maxDegree - 1) {
            directionUp = false;
        }
    }

    /**
     * @param bound
     * @modifies arcAngle, arc
     * @effects Change the size of the angle.
     *          First move arcAngle from it initial value to 359 degrees, than move it back to 0, and again.
     */
    @Override
    public void step(Rectangle bound) {
        if (directionUp) {
            arcAngle++;
        } else {
            arcAngle--;
        }

        if (arcAngle == minDegree || arcAngle == maxDegree - 1) {
            directionUp = !directionUp;
        }

        arc.fillArc(getLocation().x, getLocation().y, this.bound.width, this.bound.height, startAngle, arcAngle);
        // TODO :: Check rep
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
        // TODO :: Check rep
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
}
