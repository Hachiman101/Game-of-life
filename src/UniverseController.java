import javax.swing.*;

public class UniverseController {
    private final int size;
    private Universe universe;
    private JPanel panelGrid;
    private UniverseView view;

    public UniverseController(int size) {
        this.size = size;
        view = new UniverseView();
    }

    /**
     * Generate initial universe
     */
    public void generateInitialUniverse() {
        universe = new Universe(size);
        universe.countAlive();
    }

    /**
     * Generate next universe
     */
    public void generateNextUniverse() {
        universe.nextGeneration();
        universe.countAlive();
    }

    /**
     * Display empty GUI
     */
    public void displayGUI() {
        view.setVisible(true);
        panelGrid = view.getPanelGrid();
        updateGUI();
    }

    /**
     * Update the GUI with universe information
     */
    public void updateGUI() {
        panelGrid.removeAll();
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                view.drawRectangle(universe.universeCurrent[j][k]);
            }
        }

        view.setLabelGeneration(universe.currentGeneration);
        view.setLabelAlive(universe.currentAlive);
        panelGrid.validate();
        panelGrid.repaint();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
