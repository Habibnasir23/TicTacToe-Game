public class Board  { // Board class

    char[] gameBoard = new char[9]; // 3*3 array for gameBoard
    public static final int SIZE = 9; // variable to store size of the board
    public static final char EMPTY = '+'; // variable to store empty position character
    public Board() {

        //Initialize the array
        for (int i = 0; i < SIZE; i++) {
            gameBoard[i] = '+';
        }

    }

    public  void drawBoard()// display function to display the content of the array( tic tac toe)
    {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| " + gameBoard[i] + " |");
            if ((i + 1) % 3 == 0)
                System.out.println();

        }

        System.out.println();
    }

    /////////////////////////////////

    public void boardReset()// This function resets the Board to all + spaces
    {
        for (int i = 0; i < SIZE; i++) {
            gameBoard[i] = EMPTY;
        }
    }

    // Finding if someone won or not
    public char playerHasWon() {
        char checkH = 'H';
        char checkC = 'C';

        //checking each row if someone won
        for (int i = 0; i< 9; i+=3)
        {
            if (gameBoard[i] == checkH && gameBoard[i+1] == checkH && gameBoard[i+2] == checkH) {
                return checkH;
            }
            else if(gameBoard[i] == checkC && gameBoard[i+1] == checkC && gameBoard[i+2] == checkC) {
                return checkC;
            }
        }

        //checking each column if someone won
        for (int j = 0; j <3; j++)
        {
            if (gameBoard[j] == checkH && gameBoard[j+3] == checkH && gameBoard[j+6] == checkH) {
                return checkH;
            }
            if (gameBoard[j] == checkC && gameBoard[j+3] == checkC && gameBoard[j+6] == checkC) {
                return checkC;
            }
        }

        //Check each row

        //Check each column

        /////////////////////////////////////////

        String sumOfChars = "";// making a string from the array elements.
        for( int index = 0; index < SIZE; index++)
        {
            sumOfChars = sumOfChars + gameBoard[index];

        }

        if (sumOfChars.indexOf(EMPTY) < 0)// looking if there is a '+' left.
        // if not then it means the gameBoard is full and it is a tie
        {	return 'T';}

        //Check the diagonals
        // Checking first diagonal
        if (gameBoard[0] == checkH && gameBoard[4] == checkH && gameBoard[8] == checkH) {
            return checkH;
        }
        else if (gameBoard[0] == checkC && gameBoard[4] == checkC && gameBoard[8] == checkC) {
            return checkC;
        }

        // checking second diagonal
        if (gameBoard[2] == checkH && gameBoard[4] == checkH && gameBoard[6] == checkH) {
            return checkH;
        }
        else if (gameBoard[2] == checkC && gameBoard[4] == checkC && gameBoard[6] == checkC) {
            return checkC;
        }

        // Otherwise nobody has won yet
        return ' ';

    }


}