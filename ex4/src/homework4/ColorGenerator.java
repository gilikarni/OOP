package homework4;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ColorGenerator implements Observable {
    static private ColorGenerator colorGenerator = new ColorGenerator();
    private ArrayList<Panel> listeners = new ArrayList<>();
    NotificationStrategy notificationStrategy = new IncreasingNotificationStrategy();
    final static int NUMBER_OF_PANELS = 25;
    private ArrayList<Integer> order = null;
    private int indexInOrder = 0;
    Color color = null;

    /** Abstraction Function:
     * ColorGenerator c is a singleton that is responsible for creating a new color every two seconds and update all the
     * listeners about the new color.
     *
     * Representation Invariant:
     * There is always only one object of type ColorGenerator.
     * There no more than 25 listeners.
     */
    private ColorGenerator() {
        checkRep();
    }

    /**
     * @effects verify that the representation invariant holds.
     */
    private void checkRep() {
        assert colorGenerator != null : "colorGenerator is null";
        assert listeners.size() > NUMBER_OF_PANELS : "There are " + listeners.size() + "listeners";
    }

    /**
     * @return the single instance of ColorGenerator.
     */
    public static ColorGenerator getInstance() {
        return colorGenerator;
    }

    /**
     * @effects add a panel listener to the board.
     */
    @Override
    public void addListener(InvalidationListener listener) {
        assert listener instanceof Panel;
        listeners.add(listener);
        checkRep();
    }

    /**
     * @effects remove a panel listener to the board.
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        assert listener instanceof Panel;
        listeners.remove(listener);
        checkRep();
    }

    /**
     * @effects set a new notification strategy
     * @modifies this
     */
    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
        checkRep();
    }

    /**
     * @effects generates a new random color and update all the panels about it. If all the panels wasn't added yet the
     * function will do nothing.
     */
    public void updateNextPanel() throws InterruptedException {
        if (listeners.size() < NUMBER_OF_PANELS) { /* The panels wasn't added yet */
            return;
        }

        if (indexInOrder == 0) {
            order = (ArrayList<Integer>) notificationStrategy.getOrder();
            Random randomColorGenerator = new Random();
            float r = randomColorGenerator.nextFloat();
            float g = randomColorGenerator.nextFloat();
            float b = randomColorGenerator.nextFloat();
            color = new Color(r, g, b);
        }

        listeners.get(order.get(indexInOrder)).update(this, color);
        indexInOrder++;
        indexInOrder %= NUMBER_OF_PANELS;
        checkRep();
    }
}
