/**
 * Created by kevinchen on 10/16/15.
 */
//class with ConnectFour logic
import java.util.*;

class ConnectFour
{
    //0 means empty, 1 means red, 2 means yellow
    private int [][] mat;

    public ConnectFour()
    {
        mat = new int [6][7];
    }

    public ConnectFour(int[][] m)
    {
        mat = m;
    }

    public int[][] getMatrix()
    {
        return mat;
    }

    public ConnectFour duplicate()
    {
        int[][] newMatrix = new int[6][7];
        for(int r = 0; r < 6; r++)
            for(int c = 0; c < 7; c++)
                newMatrix[r][c] = this.getMatrix()[r][c];
        return new ConnectFour(newMatrix);
    }

    public int getColor(int row, int col)
    {
        return mat[row][col];
    }

    public void reset()
    {
        for(int r = 0; r < 6; r++)
        {
            for(int c = 0; c < 7; c++)
                mat[r][c] = 0;
        }
    }

    public int dropRed(int col)
    {
        for(int k = 5; k >= 0; k--)
        {
            if(mat[k][col] == 0)
            {
                mat[k][col] = 1;
                return k;
            }
        }
        return -1;
    }

    public int dropYellow(int col)
    {
        for(int k = 5; k >= 0; k--)
        {
            if(mat[k][col] == 0)
            {
                mat[k][col] = 2;
                return k;
            }
        }
        return -1;
    }

    public int checkWinner()
    {
        //4 in a row
        for(int r = 0; r < 6; r++)
        {
            for(int c = 0; c < 4; c++)
                if(mat[r][c] != 0 &&  mat[r][c] == mat[r][c+1] && mat[r][c+1] == mat[r][c+2] && mat[r][c+2] == mat[r][c+3])
                    return mat[r][c];
        }

        //4 in a column
        for(int c = 0; c < 7; c++)
        {
            for(int r = 0; r < 3; r++)
                if(mat[r][c] != 0 && mat[r][c] == mat[r+1][c] && mat[r+1][c] == mat[r+2][c] && mat[r+2][c] == mat[r+3][c])
                    return mat[r][c];
        }

        //4 in a left-up to right-down diagonal
        for(int r = 0; r < 3;r++)
        {
            for(int c = 0; c < 4; c++)
                if(mat[r][c] !=0 && mat[r][c] == mat[r+1][c+1] && mat[r+1][c+1] == mat[r+2][c+2] && mat[r+2][c+2] == mat[r+3][c+3])
                    return mat[r][c];
        }

        //4 in a right-up to left-down diagonal
        for(int r = 0; r < 3; r++)
        {
            for(int c = 3; c < 7; c++)
            {
                if(mat[r][c] != 0 && mat[r][c] == mat[r+1][c-1] && mat[r+1][c-1] == mat[r+2][c-2] && mat[r+2][c-2] == mat[r+3][c-3])
                    return mat[r][c];
            }
        }

        return 0;
    }

    //Check for the number of red threes
    public int checkRedThrees()
    {
        int count = 0;
        //3 in a row
        for(int r = 0; r < 6; r++)
        {
            for(int c = 0; c < 4; c++)
            {
                if(mat[r][c] == 1 &&  mat[r][c] == mat[r][c+1] && mat[r][c+1] == mat[r][c+2] && mat[r][c+3] == 0)
                {
                    count++;
                }
            }

        }

        for(int r = 0; r < 6; r++)
        {
            for(int c = 1; c < 5; c++)
            {
                if(mat[r][c] == 1 &&  mat[r][c] == mat[r][c+1] && mat[r][c+1] == mat[r][c+2] && mat[r][c-1] == 0)
                {
                    count++;
                }
            }

        }

        //3 in a column
        for(int c = 0; c < 7; c++)
        {
            for(int r = 5; r >= 3; r--)
            {
                if(mat[r][c] == 1 && mat[r][c] == mat[r-1][c] && mat[r-1][c] == mat[r-2][c] && mat[r-3][c] == 0)
                {
                    count++;
                }
            }
        }

        //3 in a left-up to right-down diagonal
        for(int r = 5; r >= 3;r--)
        {
            for(int c = 0; c < 4; c++)
            {
                if(mat[r][c] == 1 && mat[r][c] == mat[r-1][c+1] && mat[r-1][c+1] == mat[r-2][c+2] && mat[r-3][c+3] == 0)
                {
                    count++;
                }
            }
        }

        //3 in a right-up to left-down diagonal
        for(int r = 5; r >= 3; r--)
        {
            for(int c = 3; c < 7; c++)
            {
                if(mat[r][c] == 1 && mat[r][c] == mat[r-1][c-1] && mat[r-1][c-1] == mat[r-2][c-2] && mat[r-3][c-3] == 0)
                {
                    count++;
                }
            }
        }
        return count;
    }

    //check for the number of yellow threes
    public int checkYellowThrees()
    {
        int count = 0;
        //3 in a row
        for(int r = 0; r < 6; r++)
        {
            for(int c = 0; c < 4; c++)
            {
                if(mat[r][c] == 2 &&  mat[r][c] == mat[r][c+1] && mat[r][c+1] == mat[r][c+2] && mat[r][c+3] == 0)
                {
                    count++;
                }
            }

        }

        for(int r = 0; r < 6; r++)
        {
            for(int c = 1; c < 5; c++)
            {
                if(mat[r][c] == 2 &&  mat[r][c] == mat[r][c+1] && mat[r][c+1] == mat[r][c+2] && mat[r][c-1] == 0)
                {
                    count++;
                }
            }

        }

        //3 in a column
        for(int c = 0; c < 7; c++)
        {
            for(int r = 5; r >= 3; r--)
            {
                if(mat[r][c] == 2 && mat[r][c] == mat[r-1][c] && mat[r-1][c] == mat[r-2][c] && mat[r-3][c] == 0)
                {
                    count++;
                }
            }
        }

        //3 in a left-up to right-down diagonal
        for(int r = 5; r >= 3;r--)
        {
            for(int c = 0; c < 4; c++)
            {
                if(mat[r][c] == 2 && mat[r][c] == mat[r-1][c+1] && mat[r-1][c+1] == mat[r-2][c+2] && mat[r-3][c+3] == 0)
                {
                    count++;
                }
            }
        }

        //3 in a right-up to left-down diagonal
        for(int r = 5; r >= 3; r--)
        {
            for(int c = 3; c < 7; c++)
            {
                if(mat[r][c] == 2 && mat[r][c] == mat[r-1][c-1] && mat[r-1][c-1] == mat[r-2][c-2] && mat[r-3][c-3] == 0)
                {
                    count++;
                }
            }
        }
        return count;
    }

    //Minimax algorithm
    //Recursion, with depth as exit parameter
    //Returns ArrayList length 7 of int arrays length of 2, ["value" of this choice, column]
    public ArrayList<Integer> AI(int depth)
    {
        //Create returned ArrayList that will contain values for all leaves of this particular parent
        ArrayList<Integer> leaves = new ArrayList();

        //Create 7 duplicate boards for the seven possible moves to be made
        ConnectFour testBoard1 = this.duplicate();
        ConnectFour testBoard2 = this.duplicate();
        ConnectFour testBoard3 = this.duplicate();
        ConnectFour testBoard4 = this.duplicate();
        ConnectFour testBoard5 = this.duplicate();
        ConnectFour testBoard6 = this.duplicate();
        ConnectFour testBoard7 = this.duplicate();

        //Make sure the board given does not already satisfy a victory condition
        if(this.checkWinner() == 0)
        {
            //Drop pieces into each board to simulate every scenario; drop color based on depth
            if (depth % 2 == 1) {
                testBoard1.dropYellow(0);
                testBoard2.dropYellow(1);
                testBoard3.dropYellow(2);
                testBoard4.dropYellow(3);
                testBoard5.dropYellow(4);
                testBoard6.dropYellow(5);
                testBoard7.dropYellow(6);
            } else {
                testBoard1.dropRed(0);
                testBoard2.dropRed(1);
                testBoard3.dropRed(2);
                testBoard4.dropRed(3);
                testBoard5.dropRed(4);
                testBoard6.dropRed(5);
                testBoard7.dropRed(6);
            }
        }

        //Base case, when depth is 0
        if (depth <= 0)
        {
            //Check to see if victory condition or threes exist and assign value to appropriate array
            if (testBoard1.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard1.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard1.checkRedThrees() - testBoard1.checkYellowThrees());

            if (testBoard2.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard2.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard2.checkRedThrees() - testBoard2.checkYellowThrees());

            if (testBoard3.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard3.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard3.checkRedThrees() - testBoard3.checkYellowThrees());

            if (testBoard4.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard4.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard4.checkRedThrees() - testBoard4.checkYellowThrees());

            if (testBoard5.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard5.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard5.checkRedThrees() - testBoard5.checkYellowThrees());

            if (testBoard6.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard6.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard6.checkRedThrees() - testBoard6.checkYellowThrees());

            if (testBoard7.checkWinner() == 1)
                leaves.add(1000);
            else if (testBoard7.checkWinner() == 2)
                leaves.add(-1000);
            else
                leaves.add(testBoard7.checkRedThrees() - testBoard7.checkYellowThrees());

            return leaves;
        }

        //Create the minimum/maximum value found in each leaf's ArrayList of leaves
        int minmax1 = 0;
        int minmax2 = 0;
        int minmax3 = 0;
        int minmax4 = 0;
        int minmax5 = 0;
        int minmax6 = 0;
        int minmax7 = 0;

        //Iterate through each of the seven leaves' ArrayList of leaves to find minimum/maximum value
        for(int i = 0; i < 7; i++)
        {
            ArrayList<Integer> case1 = testBoard1.AI(depth-1);
            ArrayList<Integer> case2 = testBoard2.AI(depth-1);
            ArrayList<Integer> case3 = testBoard3.AI(depth-1);
            ArrayList<Integer> case4 = testBoard4.AI(depth-1);
            ArrayList<Integer> case5 = testBoard5.AI(depth-1);
            ArrayList<Integer> case6 = testBoard6.AI(depth-1);
            ArrayList<Integer> case7 = testBoard7.AI(depth-1);

            //If the depth is odd, find the maximum value in each ArrayList
            if(depth % 2 == 1)
            {
                if(case1.get(i) > minmax1)
                    minmax1 = case1.get(i);
                if(case2.get(i) > minmax2)
                    minmax2 = case2.get(i);
                if(case3.get(i) > minmax3)
                    minmax3 = case3.get(i);
                if(case4.get(i) > minmax4)
                    minmax4 = case4.get(i);
                if(case5.get(i) > minmax5)
                    minmax5 = case5.get(i);
                if(case6.get(i) > minmax6)
                    minmax6 = case6.get(i);
                if(case7.get(i) > minmax7)
                    minmax7 = case7.get(i);
            }

            //If depth is even, find the minimum value in each ArrayList
            if(depth % 2 == 0)
            {
                if(case1.get(i) < minmax1)
                    minmax1 = case1.get(i);
                if(case2.get(i) < minmax2)
                    minmax2 = case2.get(i);
                if(case3.get(i) < minmax3)
                    minmax3 = case3.get(i);
                if(case4.get(i) < minmax4)
                    minmax4 = case4.get(i);
                if(case5.get(i) < minmax5)
                    minmax5 = case5.get(i);
                if(case6.get(i) < minmax6)
                    minmax6 = case6.get(i);
                if(case7.get(i) < minmax7)
                    minmax7 = case7.get(i);
            }
        }

        //Add each minimum/maximum value to the ArrayList of leaves of this parent

        leaves.add(minmax1);
        leaves.add(minmax2);
        leaves.add(minmax3);
        leaves.add(minmax4);
        leaves.add(minmax5);
        leaves.add(minmax6);
        leaves.add(minmax7);

        //For troubleshooting
        //System.out.println(leaves);
        return leaves;
    }
}
