import java.awt.*;
import java.util.Random;


/**
 * A ColorAndLocationChaningShape is a Shape that can change its location and color using its step()
 * method.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationAndColorChangingShape extends LocationChangingShape{

    /*
    Abstraction Function:
    A LocationChangingShape l is located at location with the color color. l can move in the speed l.super.velocity.x
    horizontally and in l.super.velocity.y vertically.

    Representation invariant for every LocationAndColorChangingShape l:
    Representation invariant of LocationChangingShape
    */


    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationAndColorChangingShape(Point location, Color color) {
        super(location, color);
    }

    @Override
    /**
     * @modifies this
     * @effects Changes the location of this as described in the specification
     *          of LocationChangingShape.step(Rectangle bound) &&
	 *			if the velocity of this needs to be changed (as described in LocationChangingShape.step(Rectangle bound)),
	 *			changes the color of this to a new random color;
	 *			else, does not change the color of this.
     */
    public void step(Rectangle bound) {
        Point oldVelocity = new Point(this.getVelocityX(), this.getVelocityY());
        super.step(bound);
        if (oldVelocity.x != this.getVelocityX() || oldVelocity.y != getVelocityY()) { /* The velocity had changed */
            Random randomColorsGenerator = new Random();

            float r = randomColorsGenerator.nextFloat();
            float g = randomColorsGenerator.nextFloat();
            float b = randomColorsGenerator.nextFloat();

            Color randomColor = new Color(r, g, b);
            this.setColor(randomColor);
        }
    }
}
