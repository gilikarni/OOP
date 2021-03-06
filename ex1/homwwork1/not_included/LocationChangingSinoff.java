import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class LocationChangingSinoff extends LocationChangingShape{
    private Dimension size;
    private final java.lang.String imagePath = "sinoff.jpg";
    BufferedImage img;

     /*
    Abstraction Function:
    A LocationChangingSinoff l is a LocationChangingShape.
    1 is viewed as an coloured color oval with the bounding rectangle of size size,
    located at locations

    Representation invariant for every LocationChangingOval l:
    Representation invariant for LocationChangingShape &&
    size != null
    */

    @Override
    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is
     * violated.
     */ protected void checkRep() {
        super.checkRep();
        assert size != null: "size is null";
        assert img != null: "img is null";
    }

    /**
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingSinoff(Point location, Color color, Dimension size) {
        super(location, color);

        if (size == null) {
            throw new NullPointerException();
        }

        this.size = new Dimension(size);
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            assert false: "image not found";
        }
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

    /**
     * @return the blocking rectangle of this.oval
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    /**
     * @effects draw g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, (int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(),
                (int) size.getHeight(), getColor(), null);
    }

    /**
     * @modifies this
     * @effects Sets color of this.
     */
    @Override
    public void setColor(Color color) {
        super.setColor(color);
        checkRep();
    }
}
