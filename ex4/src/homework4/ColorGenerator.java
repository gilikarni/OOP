package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * ColorGenerator is a class responsible for generating a new color every 2 seconds.
 * ColorGenerator is an Observable with Panels as Observers - The ColorGenerator determines the color and the Panels
 * displays the colors. The Panels get the color changing using push notifications. Every Panel have to register at the
 * ColorGenerator in order to get the notifications.
 * ColorGenerator is also a Singleton - there is only one object of this kind per program. The ColorGenerator is an
 * eager singleton - it is created when the program starts.
 */
public class ColorGenerator {

    /** Abstraction Function:
     * ColorGenerator c is a singleton that is responsible for creating a new color every two seconds and update all the
     * observers about the new color.
     * this.color - the current color to update the Panels about.
     * notificationStrategy - a components that determines the order to notify the Panels - it can be changed using
     * this.setNotificationStrategy()
     * order - an array of indices that represents the order to update the panels, it changes whenever there is a new
     * color.
     * indexInOrder - the current Panel index to update in the order array
     * listeners - the Panels to update
     * colorGenerator - the instance of the ColorGenerator
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

    /**
     * @effects create a new object of type ColorGenerator.
     * When the object is created it creates two timers:
     * 1. A timer for the color changing every 2 s the ColorGenerator changes it color.
     * 2. A timer for updating the Panels about the change - every 40 ms. The ColorGenerator will update the Panels
     * about a new color only if the color has changed.
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
     * @requires listener is of type Panel and listener != null
     * @effects add a panel listener to the board.
     * @modifies this
     */
    public void addObserver(Panel listener) {
        assert listener instanceof Panel;
        listeners.add(listener);
        checkRep();
    }

    /**
     * @requires listener is of type Panel and listener != null
     * @effects remove a panel listener to the board.
     * @modifies this
     */
    public void removeObserver(Panel listener) {
        assert listener instanceof Panel;
        listeners.remove(listener);
        checkRep();
    }

    /**
     * @requires notificationStrategy != null
     * @effects set a new notification strategy to update the Panels about new colors
     * @modifies this
     */
    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
        checkRep();
    }

    /**
     * @effects notify the next Panel about the new color, if there is no new color or not all the Panels added yet -
     * do nothing.
     * @modifies this, the Panel that gets updated
     */
    private void notifyNextObserver() {
        if (listeners.size() != order.size() || /* Not enough listeners were added */
                indexInOrder == listeners.size()) { /* All the panels were notified */
            return;
        }
        Panel currentPanel = listeners.get(order.get(indexInOrder));
        currentPanel.update(color);
        indexInOrder++;
        checkRep();
    }

    /**
     * @effects generates a new random color and gets a new order from notificationStrategy.
     * @modifies this
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
