package pro.zackpollard.nqueensbfs;

import java.util.*;

/**
 * @author Zack Pollard
 */
public class State {

    //This is an array of all the positions, the array position is the X co-ordinate on the chess board
    //and the value in the array at that position is the Y co-ordinate on the chess board.
    private int[] positions;
    //The size of the chess board.
    private final int n;

    /**
     * This constructor is used to create a new root node to start the search with.
     *
     * @param n The size of the chess board.
     */
    public State(int n) {

        //Sets the objects n variable to the provided n.
        this.n = n;
        //Initiates the int array to the correct length.
        positions = new int[n];
        //Loops through the entire array and sets all values to -1 rather than 0.
        for (int i = 0; i < positions.length; i++) positions[i] = -1;
    }

    /**
     * This is used for creating the child states, which is why it is a private constructor.
     * It is used by the #getChildSolutions() method within this file.
     *
     * @param n The size of the chess board.
     * @param positions The positions of the queens on the new state.
     */
    private State(int n, int[] positions) {

        //Sets the objects n variable to the provided n.
        this.n = n;
        //Sets the objects positions variable to the provided positions.
        this.positions = positions;
    }

    /**
     * This method is used to print out this state and all of its parent states.
     * It prints a series of chessboards with the positions of the queens to the console.
     */
    public void printAllStates() {

        //Increments an int 'max' on each iteration in order to limit the print from the root object to n.
        for(int max = 0; max < positions.length; ++max) {

            //Prints a blank line for separation.
            System.out.println();

            //Increments an int 'x' on each iteration in order to print all rows.
            for(int x = 0; x < positions.length; ++x) {

                //Gets the position of the queen in the current row.
                int position = positions[x];

                //Increments an int 'y' on each iteration in order to print all columns.
                for(int y = 0; y < n; ++y) {

                    //Checks if the position of the queen equals current 'y' and the current row is less than 'max'
                    if(position == y && x <= max) {

                        //Print "Q " to signify the presence of a queen on the board.
                        System.out.print("Q ");
                    //If the space is not the location of a queen.
                    }else{

                        //Print "- " to signify there is no queen in that position.
                        System.out.print("- ");
                    }
                }

                //Prints a blank line for separation.
                System.out.println();
            }
        }
    }

    /**
     * This method prints a chessboard to console representing this state.
     */
    public void printPositions() {

        //Prints a blank line for separation.
        System.out.println();

        //Increments an int 'x' on each iteration in order to print all rows.
        for(int x = 0; x < positions.length; ++x) {

            //Gets the position of the queen in the current row.
            int position = positions[x];

            //Increments an int 'y' on each iteration in order to print all columns.
            for (int y = 0; y < n; ++y) {

                //Checks if the position of the queen equals current 'y'
                if(position == y) {

                    //Print "Q " to signify the presence of a queen on the board.
                    System.out.print("Q ");
                    //If the space is not the location of a queen.
                }else{

                    //Print "- " to signify there is no queen in that position.
                    System.out.print("- ");
                }
            }

            //Prints a blank line for separation.
            System.out.println();
        }
    }

    /**
     * Prints the positions array to the console.
     */
    public void printArray() {

        //Converts the positions array to a string, and prints it to the console.
        System.out.println(Arrays.toString(positions));
    }

    /**
     * This method is used to check whether the state is acceptable up to this point. This means that
     * the searching algorithm can remove any states that are not acceptable immediately rather than waiting until
     * the very end of the search to check whether they meet the goal state.
     *
     * @return True if the state is acceptable, false otherwise.
     */
    public boolean isAcceptable() {

        //Variable to store the current row.
        int current = 0;

        //Loops through the positions to find the first -1 or hit 'n', whichever comes first.
        while(current < n && positions[current] != -1) {

            //Adds one to the 'current' variable.
            ++current;
        }

        //Subtracts one from current to go from an index of 1 to an index of 0 to match how arrays work.
        --current;

        //Increment through all of the rows from 0 to 'n'.
        for(int i = 0; i < n; ++i) {

            //Check that the selected row isn't the same as the current row.
            //Also check that a queen has been added to the selected row.
            if(i != current && positions[i] != -1) {

                //Check if the position in the column of the currently selected row
                //is the same as the position of the current row in the column.
                if(positions[i] == positions[current]) {

                    //Return false as this state isn't acceptable.
                    return false;
                //Check to see if the selected queen is on a diagonal with the current queen.
                } else if(Math.abs((current) - i) == Math.abs(positions[current] - positions[i])) {

                    //Return false as this state isn't acceptable.
                    return false;
                }
            }
        }

        //Return true as all checks have passed successfully.
        return true;
    }

    /**
     * This method is used to check whether the state is a valid "goal state". This simply checks whether
     * the positions array has a position for the last queen, and if it does, checks that the solution #isAcceptable()
     * and then return accordingly.
     *
     * @return True if the state is a "goal state", false otherwise.
     */
    public boolean isGoal() {

        //Check if the last row has a queen in it and if it does check if the state is acceptable.
        if(positions[n - 1] != -1 && isAcceptable()) {

            //Return true as this is a goal state.
            return true;
        }

        //Return false as this is not a goal state.
        return false;
    }

    /**
     * This method is used to get all possible children of the current state. This will return a Set of the
     * child states.
     *
     * @return A Set of the possible child states or null if no child states were found.
     */
    public Set<State> getChildSolutions() {

        //Create a variable of type Set which contains State objects.
        //Initiate that Set as a LinkedHashSet (an set which maintains the order elements are entered in).
        Set<State> children = new LinkedHashSet<>();

        //Create a variable for the current row.
        int row = 0;

        //Loops through the positions to find the first -1 or hit 'n', whichever comes first.
        while(row < n && positions[row] != -1) {

            //Adds one to the 'row' variable.
            ++row;
        }

        //Check whether the row is the final row and if it is whether that row already has a queen in it.
        if(row - 1 == n && positions[row] != -1) {

            //Return null if this is the case as this state will have no children.
            return null;
        }

        //Increment through all of the rows from 0 to 'n'.
        for(int i = 0; i < n; ++i) {

            //Make a copy of the array so that we can edit it without editing the original.
            int[] childPositions = positions.clone();
            //Set the next free row to the currently set column ('i').
            childPositions[row] = i;

            //Add this child to the set of child states.
            children.add(new State(n, childPositions));
        }

        //Return the set of child states.
        return children;
    }
}
