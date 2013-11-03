package net.jef.exercise;

/**
 * Coding Exercise for stated problem:
 *
 * Write some code that accepts an integer and prints the integers from 0 to that input integer in a spiral format.
 *
 * For example, if I supplied 24 the output would be:
 *
 *      20  21  22  23  24
 *      19  6   7   8   9
 *      18  5   0   1   10
 *      17  4   3   2   11
 *      16  15  14  13  12
 *
 * @author Jeffrey McKenzie
 *
 */
public class IntSpiral {

    /**
     * Returns the dimensions of the grid needed to hold the spiral array of sequential numbers.
     * Dimensions are determined by rounding up the square root of the number + 1 indicating the
     * number of positions needed.  (Counting starts at zero.)
     *
     * @param number the number to count up to in a spiral fashion
     * @return       an array of two integers that are the X and Y of the grid
     * @throws IllegalArgumentException If number is not greater than zero
     */
    private int[] determineGridDimensions(int number) {
        if(number < 1) {
            throw new IllegalArgumentException("Number must be greater than zero to determine grid size.");
        }
        // Find the smallest square that will hold all the numbers.
        final int width = (int) Math.ceil(Math.sqrt(number + 1));
        // Check to see if we can eliminate a row if the numbers will fit; creating a rectangle.
        final int height = ((width * (width - 1)) < (number + 1) ? width : (width - 1));
        return new int[]{width, height};
    }

    /**
     * Takes the number param and creates the smallest grid required to hold all sequential numbers starting at
     * zero and ending at the number supplied.  Counting starts in the center position if possible, and radiates
     * out clockwise going in the directional order of RIGHT, DOWN, LEFT, then UP.
     *
     * @param number the number to count up to in a spiral fashion
     * @return       two-dimensional array containing the spiral grid
     * @throws IllegalArgumentException If the number is not greater than zero
     */
    public int[][] spiral(int number) {
        if(number <= 0) {
            throw new IllegalArgumentException("Creating a spiral of integers requires a number greater than zero");
        }
        // Set the size of the grid based on the number.
        final int[] gridDimensions = determineGridDimensions(number);
        final int width = gridDimensions[0];
        final int height = gridDimensions[1];
        // Set the starting point or origin.  This is the anchor point for rotating around the grid based
        // on what layer is being populated.
        // If the width or height is an even number, move the origin one position to the top or left (or both)
        // to keep the spiral from going off the edge.
        final int originX = (width % 2 == 0) ? ((width / 2) - 1) : (width / 2);
        final int originY = (height % 2 == 0) ? ((height / 2) - 1) : (height / 2);
        // Set up the values to track and move the current position and the current layer.
        int currentX = originX;
        int currentY = originY;
        int layer = 1;
        // Create the grid as a two dimensional array of int's.
        int[][] grid = new int[height][width];
        //  Populate the origin with 0.
        grid[currentY][currentX] = 0;
        // Start the iterations at 1 and increment until the number is reached.
        int currentNumber = 1;
        // Move around the grid clockwise with directions moving from RIGHT to DOWN to LEFT to UP.
        while(currentNumber <= number) {
            // The boundaries for each layer will be between (origin - layer) and (origin + layer).
            // Go RIGHT to the boundary.
            while((currentX < (originX + layer)) && (currentNumber <= number)) {
                currentX++;
                grid[currentY][currentX] = currentNumber++;
            }
            // Go DOWN to the boundary.
            while((currentY < (originY + layer)) && (currentNumber <= number)) {
                currentY++;
                grid[currentY][currentX] = currentNumber++;
            }
            // Go LEFT to the boundary.
            while((currentX > (originX - layer)) && (currentNumber <= number)) {
                currentX--;
                grid[currentY][currentX] = currentNumber++;
            }
            // Go UP to the boundary.
            while((currentY > (originY - layer)) && (currentNumber <= number)) {
                currentY--;
                grid[currentY][currentX] = currentNumber++;
            }
            // Increment layer to spiral out to the next layer of rows and columns on the next iteration.
            layer++;
        }
        return grid;
    }

    /**
     * Prints the spiral grid to standard output.  One line for each row, columns separated by tabs.
     *
     * @param spiralGrid two-dimensional array containing the spiral grid
     */
    public void printSpiral(int[][] spiralGrid) {
        for(int[] row : spiralGrid){
            for(int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //  Test driver to view output on standard output.
        IntSpiral ns = new IntSpiral();
        for(int i = 1; i < 100; i++) {
            int[][] spiralGrid = ns.spiral(i);
            ns.printSpiral(spiralGrid);
            System.out.println("********************");
        }
     }
}

