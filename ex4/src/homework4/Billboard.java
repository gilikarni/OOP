package homework4;

import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main application class for exercise #4.
 * This application form a billboard of 25 panels, arranged in 5X5.
 * Every two seconds, the billboard changes color in a certain strategy.
 */
@SuppressWarnings("serial")
public class Billboard extends JFrame implements ActionListener {
    /** Abstraction Function:
     * Billboard represents an application of a board of 5X5 color changing panels, s.t.:
     *  color change happens every 2 seconds by colorGenerator.
     *  the panels are notified 40ms one after the other by colorGenerator.
     *
     * Representation Invariant:
     * Representation Invariant of JFrame and
     * panels is not null
     */

    // preferred frame width and height.
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int PANEL_NUMBER_PER_ROW = 5;
    private static final int PANEL_NUMBER_PER_COLUMN = 5;
    private static final int SPACING = 100;
    private static final int PANEL_HEIGHT = (WINDOW_HEIGHT - SPACING) / PANEL_NUMBER_PER_COLUMN;
    private static final int PANEL_WIDTH = WINDOW_WIDTH / PANEL_NUMBER_PER_ROW;


    // graphical components
    private JMenuBar menuBar;
    private JMenu fileMenu, updateOrderItem, helpMenu;
    private JMenuItem exitItem, aboutItem,
            increasingItem, randomItem, twoTimesItem, columnItem;
    private JPanel mainPanel;

    //  color generator and array shape
    private ColorGenerator colorGenerator;
    private ArrayList<Shape> panels = new ArrayList<>();

    /**
     * @modifies this
     * @effects Initializes the GUI, adds all panels to the panels array
     */
    public Billboard() {
        super("Billboard");

        // create main panel and menubar
        mainPanel = (JPanel)createMainPanel();
        getContentPane().add(mainPanel);
        menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // get colorGenerator object
        colorGenerator = ColorGenerator.getInstance();

        // create panels
        for (int i=0; i < 5; i++){
            for (int j=0; j <5; j++){
                Panel new_panel = new Panel(new Point(j*PANEL_WIDTH, SPACING + i*PANEL_HEIGHT), new Color(255, 255, 255),
                        new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
                panels.add(new_panel);
                colorGenerator.addObserver(new_panel);
            }
        }

        // enable repaint timer (ticks 25 times per second)
        Timer paintTimer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();  // make sure that the shapes are redrawn
            }
        });
        paintTimer.start();
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
     * @return main GUI menu bar
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        updateOrderItem = new JMenu("Change Update Order");
        increasingItem = new JMenuItem("By increasing order");
        increasingItem.addActionListener(this);
        updateOrderItem.add(increasingItem);
        columnItem = new JMenuItem("By columns");
        columnItem.addActionListener(this);
        updateOrderItem.add(columnItem);
        randomItem = new JMenuItem("By random order");
        randomItem.addActionListener(this);
        updateOrderItem.add(randomItem);
        twoTimesItem = new JMenuItem("Checkered");
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
        for (Shape panel : panels) {
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
     * @effects Billboard application.
     */
    public static void main(String[] args) {
        Billboard application = new Billboard();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
