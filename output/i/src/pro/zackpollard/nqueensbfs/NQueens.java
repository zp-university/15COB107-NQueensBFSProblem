package pro.zackpollard.nqueensbfs;

import java.util.Scanner;

/**
 * @author Zack Pollard
 */
public class NQueens {

    /**
     * This constructor simply allows the Main class and main method run the rest of the program.
     */
    public NQueens() {

        //Creates a new Scanner which is used for getting input from the console.
        Scanner scanner = new Scanner(System.in);

        //Variable for use later.
        String line;

        //Simple print to tell the user what we want them to input.
        System.out.print("Please enter the number of queens you would like: ");

        //This loop will run until the input is "quit" or "exit". Each time the loop runs it grabs the next line
        //of input from the console and runs the code inside the loop against that.
        while((line = scanner.nextLine()) != null && line != "quit" && line != "exit") {

            //Used to store the n value entered by the user.
            int amount;

            try {
                //Parse the string value to an integer.
                amount = Integer.parseInt(line);
            } catch(NumberFormatException e) {
                //Error if the string entered was not a number and ask for input again.
                System.err.println("The provided number was not an integer.");
                System.out.print("Please enter the number of queens you would like: ");
                continue;
            }

            //Checks that the value of n provided is within the bounds that were specified by the specification.
            if(amount < 11 && amount > 3) {

                //Creates a new ProblemCalculator object, passing the value of n that was
                //provided and then telling the calculator to run.
                new ProblemCalculator(amount).run();
            }

            //Print out an empty line to keep the console a little cleaner and then ask for another input.
            System.out.println();
            System.out.print("Please enter the number of queens you would like: ");
        }
    }
}
