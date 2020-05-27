public class Main {
    public static void main(String[] args) {
        int size = 20;
        int generations = 10;

        UniverseController universeController = new UniverseController(size);
        universeController.generateInitialUniverse();
        universeController.displayGUI();
        for (int i = 0; i < generations; i++) {
            universeController.generateNextUniverse();
            universeController.updateGUI();
        }
    }
}
