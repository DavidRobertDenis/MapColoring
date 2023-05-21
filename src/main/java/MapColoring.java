import java.util.Arrays;

public class MapColoring {
    private int[][] adjacencyMatrix;
    private int[] colorAssignment;
    private int numCountries;
    private int numColors;

    public MapColoring(int[][] adjacencyMatrix, int numColors) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numCountries = adjacencyMatrix.length;
        this.numColors = numColors;
        this.colorAssignment = new int[numCountries];
    }

    public void solve() {
        if (colorMap(0)) {
            displayColoring();
        } else {
            System.out.println("Nu există o soluție de colorare pentru această hartă.");
        }
    }

    private boolean colorMap(int country) {
        if (country == numCountries) {
            return true;
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(country, color)) {
                colorAssignment[country] = color;

                if (colorMap(country + 1)) {
                    return true;
                }

                colorAssignment[country] = 0; // undo the assignment
            }
        }

        return false;
    }

    private boolean isSafe(int country, int color) {
        for (int i = 0; i < numCountries; i++) {
            if (adjacencyMatrix[country][i] == 1 && colorAssignment[i] == color) {
                return false;
            }
        }
        return true;
    }

    private void displayColoring() {
        System.out.println("Colorarea hărții:");
        for (int i = 0; i < numCountries; i++) {
            System.out.println("Țara " + (i + 1) + ": Culoare " + colorAssignment[i]);
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 1, 1, 1},  // Matricea de adiacență a hărții
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };

        int numColors = 4; // Numărul maxim de culori disponibile

        MapColoring mapColoring = new MapColoring(adjacencyMatrix, numColors);
        mapColoring.solve();
    }
}