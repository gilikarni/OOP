import java.awt.*;

public class LocationChangingNumberedOval extends LocationChangingOval {
    private static Integer counter = 0;
    private Integer myNumber = 0;

    /*
    Abstraction Function:
    A LocationChangingNumberedOval l is a LocationChangingOval numbered with the positive Integer
    MyNumber

    Representation invariant for every LocationChangingNumberedOval l:
    Representation invariant for LocationChangingOval &&
    myNumber != null && myNumber > 0
    */

    @Override
    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */ protected void checkRep() {
        super.checkRep();
        assert myNumber != null :
                "myNumber is null";
        assert myNumber > 0 :
                "myNumber is non positive";
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
     * shape has a number myNumber
     */
    LocationChangingNumberedOval(Point location, Color color, Dimension size) {
        super(location, color, size);

        myNumber = ++counter;
        checkRep();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = (int)(getBounds().getCenterX() / 2);
        int y = (int)(getBounds().getCenterY() / 2);
        g.drawString(myNumber.toString(), x, y);
    }

    public static void clearCounter() {
        counter = 0;
    }
}
