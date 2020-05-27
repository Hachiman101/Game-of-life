import java.util.Random;

public class Universe {
    int size;
    boolean[][] universeCurrent;
    boolean[][] universeNext;
    int[][] neighboursCount;
    int currentGeneration = 0;
    int currentAlive = 0;

    Universe(int size) {
        this.size = size;
        this.universeCurrent = new boolean[size][size];
        this.universeNext = new boolean[size][size];
        generateInitialUniverse();
        findNeighbour();
    }

    /**
     * Generate initial universe using Random.nextBoolean().
     */
    public void generateInitialUniverse() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universeCurrent[i][j] = random.nextBoolean();
            }
        }
    }

    /**
     * Find all neighbours for all cells of the current universe.
     * Due to side-wrapping rule of 'Game of Life', it is using a new universe, expandedUniverse,
     * which is expanded by 1 on each side to make searching for edge neighbours easier.
     */
    public void findNeighbour() {
        boolean[][] expandedUniverse = new boolean[size + 2][size + 2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                expandedUniverse[i + 1][j + 1] = universeCurrent[i][j];
            }
            // Handle sides
            expandedUniverse[0][i + 1] = universeCurrent[size - 1][i]; // TOP SIDE
            expandedUniverse[size + 1][i + 1] = universeCurrent[0][i]; // BOTTOM SIDE
            expandedUniverse[i + 1][0] = universeCurrent[i][size - 1]; // LEFT SIDE
            expandedUniverse[i + 1][size + 1] = universeCurrent[i][0]; // RIGHT SIDE
        }

        // Handle corners
        expandedUniverse[0][0] = universeCurrent[size - 1][size - 1]; // TOP LEFT
        expandedUniverse[size + 1][size + 1] = universeCurrent[0][0]; // BOTTOM RIGHT
        expandedUniverse[size + 1][0] = universeCurrent[0][size - 1]; // BOTTOM LEFT
        expandedUniverse[0][size + 1] = universeCurrent[size - 1][0]; // TOP RIGHT

        // Count the neighbours
        neighboursCount = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int count = 0;
                if (expandedUniverse[i - 1][j - 1])
                    count++;
                if (expandedUniverse[i - 1][j])
                    count++;
                if (expandedUniverse[i - 1][j + 1])
                    count++;
                if (expandedUniverse[i][j - 1])
                    count++;
                if (expandedUniverse[i][j + 1])
                    count++;
                if (expandedUniverse[i + 1][j - 1])
                    count++;
                if (expandedUniverse[i + 1][j])
                    count++;
                if (expandedUniverse[i + 1][j + 1])
                    count++;
                neighboursCount[i - 1][j - 1] = count;
            }
        }
    }

    /**
     * Generate the next universe based on standard rules of 'Game of Life'.
     */
    public void nextGeneration() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (neighboursCount[i][j] < 2 && universeCurrent[i][j]) {
                    universeNext[i][j] = false;
                } else if (neighboursCount[i][j] > 3 && universeCurrent[i][j]) {
                    universeNext[i][j] = false;
                } else if (neighboursCount[i][j] == 3 && !universeCurrent[i][j]) {
                    universeNext[i][j] = true;
                } else {
                    universeNext[i][j] = universeCurrent[i][j];
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universeCurrent[i][j] = universeNext[i][j];
            }
        }
        currentGeneration++;
        findNeighbour();
    }

    /**
     * Count the number of alive cells in the current universe.
     */
    public void countAlive() {
        int currentAlive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universeCurrent[i][j]) {
                    currentAlive++;
                }
            }
        }
        this.currentAlive = currentAlive;
    }
}
