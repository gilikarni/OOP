import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

    private Point velocity;

    /*
    Abstraction Function:
    A LocationChangingShape l is located at location with the color color. l can move in the speed l.velocity.x
    horizontally and in l.velocity.y vertically.

    Representation invariant for every LocationChangingShape l:
    location != null && color != null &&
    -5 <= l.velocity.x <= 5 && -5 <= l.velocity.x <= 5 &&
    l.velocity.y != 0 && l.velocity.y != 0
    */

    @Override
    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */ protected void checkRep() {
        super.checkRep();
        assert -5 <= velocity.x && velocity.x <= 5 && -5 <= velocity.y && velocity.y <= 5 :
                "The vertical and horizontal speed must be in the range [-5,5]";
        assert velocity.x != 0 && velocity.y != 0 :
                "The vertical and horizontal speed must be different than zero";
    }

    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationChangingShape(Point location, Color color) {
       super(location, color);
       Random randomNumberGeneraot = new Random();
       int horizontalVelocity = 0, verticalVelocity = 0;
       while (horizontalVelocity == 0) {
           horizontalVelocity = randomNumberGeneraot.nextInt(10) - 5;
       }
       while (verticalVelocity == 0) {
           verticalVelocity = randomNumberGeneraot.nextInt(10) - 5;
       }
       velocity = new Point(horizontalVelocity, verticalVelocity);
       checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        return velocity.x;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        return velocity.y;
    }


    /**
	 * @requires -5 <= velocityX <= 5 && -5 <= velocityY <= 5
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     *          vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        velocity = new Point(velocityX, velocityY);
        checkRep();
    }


    /**
     * @modifies this
     * @effects Let p = location
     *              v = (vx, vy) = velocity
     *              r = the bounding rectangle of this
     *          If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     *              If adding v to p would move r horizontally outside of bound,
     *                  vx = -vx
     *              If adding v to p would move r vertically outside of bound,
     *                  vy = -vy
     *          }
     *          p = p + v
     */
    public void step(Rectangle bound) {
        Point curLocation = this.getLocation();

        if (bound == null) {
            throw new IllegalArgumentException();
        }

        /* Update x */
        if (curLocation.x + velocity.x <= bound.getMaxX() ||
                curLocation.x + velocity.x >= bound.getMinX()) { /* The point is in bounds */
            curLocation.x += velocity.x;
        } else { /* The point is out of bounds */
            curLocation.x -= velocity.x;
        }


        /* Update y */
        if (curLocation.y + velocity.y <= bound.getMaxY() ||
                curLocation.y + velocity.y >= bound.getMinY()) { /* The point is in bounds */
            curLocation.y += velocity.y;
        } else { /* The point is out of bounds */
            curLocation.y -= velocity.y;
        }

        this.setLocation(curLocation);
    }
}
