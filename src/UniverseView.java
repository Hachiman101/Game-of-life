import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UniverseView extends JFrame {
    private final JLabel labelGeneration = new JLabel();
    private final JLabel labelAlive = new JLabel();
    private final JPanel panelGrid = new JPanel();

    /**
     * UniverseView constructor defining its look
     */
    public UniverseView() {
        // Window properties
        super("Game Of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(485, 565);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Panel for labels
        JPanel labelsPanel = new JPanel();
        labelsPanel.setBorder(new EmptyBorder(10, 20, 5, 20)); // Padding
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        this.add(labelsPanel, BorderLayout.NORTH);

        // Label Generation
        this.labelGeneration.setText("Generation: 0");
        this.labelGeneration.setFont(new Font("Arial", Font.PLAIN, 16));
        labelsPanel.add(this.labelGeneration);

        // Label Alive
        this.labelAlive.setText("Alive: 0");
        this.labelAlive.setFont(new Font("Arial", Font.PLAIN, 16));
        labelsPanel.add(this.labelAlive);

        // Panel for grid containing cells
        this.panelGrid.setLayout(new GridLayout(20, 20, 0, 0));
        this.panelGrid.setBorder(new EmptyBorder(5, 5, 5, 5)); //padding

        this.add(this.panelGrid, BorderLayout.CENTER);
    }

    public void setLabelGeneration(int generation) {
        this.labelGeneration.setText("Generation: " + generation);
    }

    public void setLabelAlive(long aliveCount) {
        this.labelAlive.setText("Alive: " + aliveCount);
    }

    public JPanel getPanelGrid() {
        return panelGrid;
    }

    public void drawRectangle(boolean isBlack) {
        this.panelGrid.add(new DrawSquare(isBlack));
    }

    /**
     * DrawSquare class responsible for drawing a single square inside the grid
     * The square will be painted DARK_GRAY if the current cell is alive,
     * otherwise it will be painted LIGHT_GRAY
     */
    private static class DrawSquare extends JComponent {
        private final boolean isBlack;

        /**
         * Constructor
         * @param isBlack state of the current cell
         */
        public DrawSquare(boolean isBlack) {
            this.isBlack = isBlack;
        }

        /**
         * paint method used to paint a single square based on the value of isBlack boolean
         * @param graph Graphics class used for drawing onto components
         */
        public void paint(Graphics graph) {
            Graphics2D graph2d = (Graphics2D) graph;
            graph2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graph2d.setColor(isBlack ? Color.DARK_GRAY : Color.LIGHT_GRAY);

            Shape rectangle = new Rectangle(20, 20);
            graph2d.fill(rectangle);
            graph2d.draw(rectangle);
        }
    }
}
