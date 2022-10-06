import java.util.Scanner;

public class HumanPlayer extends Player {  // HumanPlayer class

    char[] gameBoard; // initializing the gameboard
    public HumanPlayer(Board obj) { // initializing HumanPlayer object
        gameBoard = obj.gameBoard; // initializing the gameBoard object
        // initialize the gameboard object
    }

    public int CompNextMove()
    {
        //taking next move input
        Scanner num = new Scanner(System.in);

        // Checking if the input is an integer
        while (!num.hasNextInt()) {
            System.out.println("This is not a valid number. Please input only a number ");
            num.nextLine();
        }
        int move = num.nextInt(); // variable to store move

        boolean flag = false; // initializing flag variable
        while (flag == false) // loop works until flag variable is false
        {
            // checking if move is between 0 & 8
            if(move < 0 || move > 8)
            {
                System.out.println("Wrong position entered. Please enter the correct position.");
                move = num.nextInt();
            }

            // checking if the position is empty
            else if (gameBoard[move] != '+')
            {
                System.out.println("This position is full. Please enter another position.");
                move = num.nextInt();
            }
            else {

                //move > 0 && move < 9 && gameBoard[move] == '+';
                gameBoard[move] = 'H';
                flag = true;
            }
            // input position from the user
        }
        return move;

    }

}