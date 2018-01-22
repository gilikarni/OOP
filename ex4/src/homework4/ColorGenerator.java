package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ColorGenerator {
    /** Abstraction Function:
     * ColorGenerator c is a singleton that is responsible for creating a new color every two seconds and update all the
     * observers about the new color.
     *
     * Representation Invariant:
     * no members are null and
     * There is always only one object of type ColorGenerator and
     * indexInOrder is not larger than size of listeners list and
     * listeners list size lte order size.
     */

    // Singleton object
    static private ColorGenerator colorGenerator = new ColorGenerator();
    private NotificationStrategy notificationStrategy;
    private ArrayList<Panel> listeners;
    private ArrayList<Integer> order = null;
    private int indexInOrder = 0;
    Color color = null;

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
        assert indexInOrder <= order.size(): "indexInOrder larger than size of order";
        assert listeners.size() <= order.size();
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
    public void addObserver(Panel listener) {
        assert listener instanceof Panel;
        listeners.add(listener);
        checkRep();
    }

    /**
     * @effects remove a panel listener to the board.
     */
    public void removeObserver(Panel listener) {
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

    private void notifyNextObserver() {
        if (listeners.size() != order.size() || /* not enough listeners were added */
                indexInOrder == listeners.size()) { /* all of the panels notified */
            return;
        }
        Panel currentPanel = listeners.get(order.get(indexInOrder));
        currentPanel.update(color);
        indexInOrder++;
        checkRep();
    }

    /**
     * @effects generates a new random color and gets a new order from notificationStrategy. If all the panels wasn't added yet the
     * function will do nothing.
     */
    private void changeColor() {
        indexInOrder = 0;
        order = (ArrayList<Integer>) notificationStrategy.getOrder();
        Random randomColorGenerator = new Random();
        float r = randomColorGenerator.nextFloat();
        float g = randomColorGenerator.nextFloat();
        float b = randomColorGenerator.nextFloat();
        color = new Color(r, g, b);
        checkRep();
    }
}
