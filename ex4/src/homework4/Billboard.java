package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Main application class for exercise #4.
 * This application form a billboard of 25 panels, arranged in 5X5.
 * Every two seconds, the billboard changes color in a certain strategy.
 */

public class Billboard extends JFrame implements ActionListener {

    // preferred frame width and height.
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int PANEL_NUMBER_PER_ROW = 5;
    private static final int PANEL_NUMBER_PER_COLUMN = 5;
    private static final int PANEL_HEIGHT = WINDOW_HEIGHT / PANEL_NUMBER_PER_COLUMN;
    private static final int PANEL_WIDTH = WINDOW_WIDTH / PANEL_NUMBER_PER_ROW;


    // graphical components
    private JMenuBar menuBar;
    private JMenu fileMenu, updateOrderItem, helpMenu;
    private JMenuItem exitItem, aboutItem,
            increasingItem, randomItem, twoTimesItem, columnItem;
    private JPanel mainPanel;
    private ColorGenerator colorGenerator;

    // panels that have been added to this
    ArrayList<Shape> panels = new ArrayList<>();

    /**
     * @modifies this
     * @effects Initializes the GUI and enables a timer that steps animation
     *          of all shapes in this 25 times per second while animation
     *          checkbox is selected.
     */
    public Billboard() {
        super("Billboard");

        // create main panel and menubar
        mainPanel = (JPanel)createMainPanel();
        getContentPane().add(mainPanel);
        menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        // get colorGenerator object
        colorGenerator = ColorGenerator.getInstance();

        // create panels
        for (int i=0; i < 5; i++){
            for (int j=0; j <5; i++){
                panels.add(new Panel(new Point(i*PANEL_WIDTH, j*PANEL_HEIGHT), new Color(0, 0, 0),
                        new Dimension(PANEL_WIDTH, PANEL_HEIGHT)));
            }
        }

        // enable panel update timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // TODO (BOM): Add code for making one animation step for all
                colorGenerator.

                repaint();  // make sure that the shapes are redrawn
            }
        });
        timer.start();

        // enable color changing timer (ticks 25 times per second)
        Timer timer = new Timer(2000, {
            // TODO: add timer logic
        });
        timer.start();
    }


    /**
     * @return main GUI panel.
     */
    private JComponent createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setBackground(Color.WHITE);

        return mainPanel;
    }


    /**
     * @return main GUI menubar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        ;
        updateOrderItem = new JMenu("Change Update Order");
        columnItem = new JMenuItem("By columns");
        columnItem.addActionListener(this);
        updateOrderItem.add(columnItem);
        increasingItem = new JMenuItem("By increasing order");
        increasingItem.addActionListener(this);
        updateOrderItem.add(increasingItem);
        randomItem = new JMenuItem("By random order");
        randomItem.addActionListener(this);
        updateOrderItem.add(randomItem);
        twoTimesItem = new JMenuItem("Sector");
        twoTimesItem.addActionListener(this);
        updateOrderItem.add(twoTimesItem);

        menuBar.add(updateOrderItem);

        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        return menuBar;
    }


    /**
     * @modifies g
     * @effects Paint this including all its shapes to g. This method is
     *          invoked by Swing to draw components. It should not be invoked
     *          directly, but the repaint method should be used instead in
     *          order to schedule the component for redrawing.
     */
    public void paint(Graphics g) {
        super.paint(g);

        //TODO (BOM): Add code for drawing all shapes in this
        ListIterator<Shape> iter = panels.listIterator();
        while (iter.hasNext()) {
            Shape panel = iter.next();
            panel.draw(getContentPane().getGraphics());
        }

    }


    /**
     * @modifies this
     * @effects Invoked when the user selects an action from the menubar
     *          and performs the appropriate operation.
     */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());

        // File->Exit: close application
        if (source.equals(exitItem)) {
            dispose();
        }

        // Change update order
        else if ((source.equals(increasingItem)) ||
                (source.equals(columnItem)) ||
                (source.equals(randomItem)) ||
                (source.equals(twoTimesItem))) {

            if (source.equals(increasingItem)) {
                colorGenerator.setNotificationStrategy(new IncreasingNotificationStrategy());
            } else if (source.equals(columnItem)) {
                colorGenerator.setNotificationStrategy(new ColumnNotificationStrategy());
            } else if (source.equals(randomItem)) {
                colorGenerator.setNotificationStrategy(new RandomNotificationStrategy());
            } else { // source.equals(twoTimesItem)
                colorGenerator.setNotificationStrategy(new TwoTimesNotificationStrategy());
            }

            repaint();
        }

        // Help->About : show about message dialog
        else if (source.equals(aboutItem)){
            JOptionPane.showMessageDialog(
                    this,
                    "Billboard - 4th" +
                            " homework assignment",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * @effects Animator application.
     */
    public static void main(String[] args) {
        Billboard application = new Billboard();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
