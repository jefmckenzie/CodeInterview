package net.jef.exercise;

import org.junit.Assert;
import org.junit.Test;

/**
 * jUnit tests for coding exercise.
 *
 * @author Jeffrey McKenzie
 */
public class IntSpiralTest {

    //Some reference data for evaluating the results.
    final int[][] twoByOne = {
            {0, 1}
    };
    final int[][] twoByTwo = {
            {0, 1},
            {3, 2}
    };
    final int[][] threeByTwo = {
            {5,0,1},
            {4,3,2}
    };
    final int[][] threeByThree = {
            {6,7,8},
            {5,0,1},
            {4,3,2}
    };
    final int[][] fiveByFour = {
            {19,6,7,8,9},
            {18,5,0,1,10},
            {17,4,3,2,11},
            {16,15,14,13,12}
    };
    final int[][] fiveByFive = {
            {20,21,22,23,24},
            {19,6,7,8,9},
            {18,5,0,1,10},
            {17,4,3,2,11},
            {16,15,14,13,12}
    };
    final int[][] incompleteFiveByFour = {
            // use 17 for input
            {0,6,7,8,9},
            {0,5,0,1,10},
            {17,4,3,2,11},
            {16,15,14,13,12}
    };
    final int[][] incompleteFiveByFive = {
            // use 22 for input
            {20,21,22,0,0},
            {19,6,7,8,9},
            {18,5,0,1,10},
            {17,4,3,2,11},
            {16,15,14,13,12}
    };
    final String noMatchString = "Spiral Grid did not match expected.";



    @Test
    public void testPerfectSquares() {
        // 2 x 2
        int[][] grid = IntSpiral.spiral(3);
        Assert.assertArrayEquals(noMatchString, twoByTwo, grid);
        // 3 x 3
        grid = IntSpiral.spiral(8);
        Assert.assertArrayEquals(noMatchString, threeByThree, grid);
        // 5 x 5
        grid = IntSpiral.spiral(24);
        Assert.assertArrayEquals(noMatchString, fiveByFive, grid);
    }

    @Test
    public void testRectangle() {
        // 3 x 2
        int[][] grid = IntSpiral.spiral(5);
        Assert.assertArrayEquals(noMatchString, threeByTwo, grid);
        // 5 x 4
        grid = IntSpiral.spiral(19);
        Assert.assertArrayEquals(noMatchString, fiveByFour, grid);
    }

    @Test
    public void testSmallNumbers() {
        int[][] grid = IntSpiral.spiral(1);
        Assert.assertArrayEquals(noMatchString, twoByOne, grid);
    }

    @Test
    public void testIncompleteGrid() {
        int[][] grid = IntSpiral.spiral(22);
        Assert.assertArrayEquals(noMatchString, incompleteFiveByFive, grid);

        grid = IntSpiral.spiral(17);
        Assert.assertArrayEquals(noMatchString, incompleteFiveByFour, grid);
    }


    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArguments() {
        int[][] grid = IntSpiral.spiral(0);
        grid[0][0] = 0;  // Added to eliminate 'not used' warning.
    }

    /**  excluded due to the verbosity of the expected result definition.
     @Test
     public void testLargeNumbers() {
     }
     **/
}
