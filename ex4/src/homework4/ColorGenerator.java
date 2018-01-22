package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ColorGenerator{
    // Singleton object
    static private ColorGenerator colorGenerator = new ColorGenerator();

    private ArrayList<Integer> order = null;
    private int indexInOrder = 0;
    Color color = null;

    /** Abstraction Function:
     * ColorGenerator c is a singleton that is responsible for creating a new color every two seconds and update all the
     * observers about the new color.
     *
     * Representation Invariant:
     * There is always only one object of type ColorGenerator.
     * indexInOrder is not larger than size of listeners list.
     */

    private ColorGenerator()
    {
        notificationStrategy = new IncreasingNotificationStrategy();
        listeners = new ArrayList<>();
        changeColor();

        // initialize timers
        // enable delay of 40ms between each message to observers
        Timer messageTimer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                notifyNextObserver();
            }
        });
        messageTimer.start();
        // enable color changing timer (ticks 1 times per  2 second)
        Timer colorTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeColor();
            }
        });
        colorTimer.start();

        checkRep();
    }

    /**
     * @effects verify that the representation invariant holds.
     * @throws AssertionError
     */
    private void checkRep() {
        assert colorGenerator != null : "colorGenerator is null";
        assert order != null: "order is null";
        assert color != null: "color is null";
        assert notificationStrategy != null: "notification strategy is null";
        assert indexInOrder <= listeners.size(): "indexInOrder larger than number of listeners";
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
