import java.awt.*;

public class LocationChangingOval extends LocationChangingShape{

    private Dimension dimension;
    private Graphics oval;

    /**
     * @param location
     * @param color
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingOval(Point location, Color color, Dimension dimension) {
        super(location, color);
        this.dimension = dimension;
        this.oval.fillOval(location.x, location.y, dimension.width, dimension.height);
    }

    @Override
    /**
     * @effects Changes the size of oval according to the given dimensions
    */
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
        if (dimension == null) {
            throw new ImpossibleSizeException();
        }

        this.dimension = (Dimension) dimension.clone();
        this.oval.fillOval(super.getLocation().x, super.getLocation().y, dimension.width, dimension.height);

        super.checkRep();
    }

    @Override
    /**
     * @return the blocking rectangle of this.oval
     */
    public Rectangle getBounds() {
        Rectangle rectangle = null;
        oval.getClipBounds(rectangle);

        return rectangle;
    }

    @Override
    /**
     * @effects draw this.oval
     */
    public void draw(Graphics g) {
        oval.drawOval(super.getLocation().x, super.getLocation().y, dimension.width, dimension.height);
    }
}
