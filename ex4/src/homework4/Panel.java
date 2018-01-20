package homework4;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Panel extends Shape implements Observer{
    private final Dimension size;

    public Panel(Point location, Color color, Dimension size){
        super(location, color);
        this.size = new Dimension(size);
    }

    @Override
    public void update(Observable o, Object arg) {
        setColor((Color)arg);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLocation(), size);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
        g.drawRect((int) getLocation().getX(), (int) getLocation().getY(), (int) size.getWidth(), (int) size.getHeight());
    }
}
