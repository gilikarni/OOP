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

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */
    @Override
    protected void checkRep() {
        super.checkRep();
        assert myNumber != null :
                "myNumber is null";
        assert myNumber > 0 :
                "myNumber is non positive";
    }

    /**
     * @requires location != null && color != null && size != null
     * @effects Initializes this with a a given location, size and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     * shape has a number myNumber
     */
    LocationChangingNumberedOval(Point location, Color color, Dimension size) {
        super(location, color, size);

        myNumber = ++counter;
        checkRep();
    }

    /**
     * @requires g != null
     * @effects draw the number of this oval to g
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = (int)(getBounds().getCenterX());
        int y = (int)(getBounds().getCenterY());
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.drawString(myNumber.toString(), x, y);
    }

    /**
     * @modifies this
     * @effects Initial the counter of the ovals to zero
     */
    public static void clearCounter() {
        counter = 0;
    }
}
