package pro.zackpollard.nqueensbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zack Pollard
 */
public class ProblemCalculator {

    private final int amount;
    private final Queue<State> queue;

    /**
     * This constructor is used by the NQueens class in order to create a
     * ProblemCalculator based on the n that was entered by the user in the console.
     *
     * @param amount
     */
    public ProblemCalculator(int amount) {

        //Sets the objects amount variable to the provided n.
        this.amount = amount;
        //Initialises the queue variable by creating a new LinkedList object (an ordered list)
        this.queue = new LinkedList<>();
    }

    /**
     * This method is called in order to start the problem calculator running.
     */
    public void run() {

        //Creates a new root node with the provided n.
        State rootNode = new State(amount);
        //Runs the bfs method and stores its solution in a variable.
        State state = this.bfs(rootNode);
        //Prints out the solution and all of its parent states.
        //state.printAllStates();
    }

    /**
     * This will run the breadth first search algorithm on the provided solution.
     * The solution provides the goal state, so this solution should work generically
     * with any problem, not just N-Queens.
     *
     * @param rootNode
     * @return
     */
    public State bfs(State rootNode) {

        long start = System.currentTimeMillis();

        //Stores all of the current states that need to be processed and have their children extracted.
        Queue<State> queue = new LinkedList<>();

        //Add the root node to the queue.
        queue.add(rootNode);
        
        //Print the root node array.
        //rootNode.printArray();

        //Continually loop until the queue is empty.
        while(!queue.isEmpty()) {

            //Get the next state in the queue, store it in a variable and remove that state from the queue.
            State state = queue.remove();

            //Get all of the child states the current state has and loop over them.
            for (State child : state.getChildSolutions()) {

                //Check if the child state is the goal state.
                if(child.isGoal()) {

                    //Print the goal state array.
                    //child.printArray();

                    System.out.println("Time taken: " + ((System.currentTimeMillis() - start) / 1000) + "s");

                    //If the child state is the goal state, end the BFS there and return that state.
                    return child;
                }

                //Checks if the child state is acceptable.
                if(child.isAcceptable()) {

                    //If the child state is an acceptable state, then add that state to the queue.
                    //This ensures that we aren't continually wasting time going
                    //over states that will never get us to the goal state.
                    queue.add(child);

                    //Print the state array.
                    //child.printArray();
                }
            }
        }

        return null;
    }
}