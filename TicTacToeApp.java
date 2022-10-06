/**
 *@author: Habib Nasir
code template designed for assignment purposes
educational and academic purpose only
 **/

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class TicTacToeApp implements ActionListener{

    final int SIZE = 9; // number of buttons
    // Buttons to hold the selection values
    JButton boardButtons[]= new JButton[SIZE];

    // buttons to restart or exit the game
    JButton bRestart;
    JButton bExit;

    // to provide status message
    JLabel gameStatusLabel;
    JFrame gWindow; // main window object

    // making the start moves random
    Random moveRandom = new Random();
    boolean moveToggleFlag = moveRandom.nextBoolean(); // toggles computer/user move
    int gameMoveCount =0; // counts the number of moves to determine draw, etc


    Board gameBoard = new Board();// creating board object
    CompPlayer compPlayer = new CompPlayer(gameBoard);  //initiliazing CompPlayer object
    int position = 0; // variable to hold position of the input
    // constructor
    public TicTacToeApp(String title) {

        // creating a JFrame window with the title
        gWindow = new JFrame(title);

        // The JPanel holds the buttons
        JPanel upperLayerPanel = new JPanel();
        upperLayerPanel.setLayout(new GridLayout(3, 3));
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i] = new JButton();
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 20));
            boardButtons[i].addActionListener(this);

            // adding the button to the Panel
            upperLayerPanel.add(boardButtons[i]);
        }

        // Panel holding buttons at the south side
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));

        bRestart = new JButton("Restart Game");
        bRestart.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bRestart.addActionListener(this);

        bExit = new JButton("Exit Game");
        bExit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bExit.addActionListener(this);


        gameStatusLabel= new JLabel("   Welcome. Your Turn. Select any button above to begin ..");
        gameStatusLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gameStatusLabel.setPreferredSize(new Dimension(100, 40));

        southPanel.add(gameStatusLabel, BorderLayout.CENTER);

        JPanel lowerButtonPanel =new JPanel();
        lowerButtonPanel.setLayout(new GridLayout(1, 2));
        lowerButtonPanel.add(bRestart, BorderLayout.WEST);
        lowerButtonPanel.add(bExit, BorderLayout.EAST);
        southPanel.add(lowerButtonPanel);


        // adding all the panels to the main window
        gWindow.setLayout(new BorderLayout());
        gWindow.add(upperLayerPanel, BorderLayout.CENTER);
        gWindow.add(southPanel, BorderLayout.SOUTH);


        gWindow.setSize(500, 500);
        gWindow.setVisible(true);
        gWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // clicking the board when the computer makes move
        if (moveToggleFlag) {
            position = compPlayer.CompNextMove();
            if (position >= 0) {
                // boardButtons[position].setText("C");
                boardButtons[position].doClick();
                gameBoard.gameBoard[position] = 'C';
            }
        }

    }



    /**
     * function to reset the buttons text values.
     */
    public void resetGame()
    {
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setForeground(Color.BLACK);
            gameBoard.boardReset();
            boardButtons[i].setEnabled(true);

        }

        gameMoveCount = 0;

        // other actions can be taken here
    }


    // Handles clicks on Compute button by computing the BMI.

    /**
     *This function performs action on the buttons
     *  @param event takes the event from the user and implement on the window
     */
    public void actionPerformed(ActionEvent event) {

        boolean isWIn = false;
        // if the event source is the restart button then
        if(event.getSource().equals(bRestart)){
            resetGame();
            this.gameStatusLabel.setText("   Game has restarted. Select any button above to begin ..");
            gWindow.setTitle("TicTacToe [Your Turn]"); // this can be randomized
        }
        if(event.getSource().equals(bExit)){
            System.exit(0);
        }
        else { // determine which cell button triggered the action event
            for(int i=0;i<SIZE;i++){
                if(event.getSource().equals(boardButtons[i])){ // button found

                    // if the cell has already been selected then do not do anything
                    if(boardButtons[i].getText().equals("H") == false && boardButtons[i].getText().equals("C") == false) {

                        // this is the selected cell number
                        gameStatusLabel.setText("  You have selected cell no " + (i+1) );

                        if(moveToggleFlag==true){
                            gWindow.setTitle("TicTacToe [Your Turn]");
                            boardButtons[i].setText("C");
                            boardButtons[i].setForeground(Color.RED);
                            if (gameBoard.playerHasWon() == 'C') {
                                isWIn = true;
                                gameStatusLabel.setText("    Computer Won! Restart the game to continue ...");
                                for (int j = 0; j<SIZE; j++) {
                                    boardButtons[j].setEnabled(false);
                                }
                            }
                        }else {
                            gWindow.setTitle("TicTacToe [Computers Turn]");

                            boardButtons[i].setText("H");
                            boardButtons[i].setForeground(Color.BLUE);
                            gameBoard.gameBoard[i] = 'H';

                            if (gameBoard.playerHasWon() == 'H') {
                                isWIn = true;
                                gameStatusLabel.setText("    Human Won! Restart the game to continue ...");
                                for (int j = 0; j<SIZE; j++) {
                                    boardButtons[j].setEnabled(false);
                                }
                            }
                        }
                        moveToggleFlag = !moveToggleFlag;
                        gameMoveCount ++; // keep counting the moves

                        if(gameMoveCount == SIZE){ // if this is the last move
                            gameStatusLabel.setText("    The Game Over! Restart the game to continue ...");
                            gWindow.setTitle("TicTacToe [Game Over!]");
                        }
                    } // new move: if condition ends

                } // main if inside the loop ends
            } // for loop ends
        } // else block ends

        // clicking the board if the computer makes move
        if (moveToggleFlag && !isWIn) {
            position = compPlayer.CompNextMove();
            if (position >= 0) {
                boardButtons[position].doClick();
                gameBoard.gameBoard[position] = 'C';
            }
        }
    } // actionPerformed function ends


    // main driver program
    public static void main(String[] args) {
        // create an object of the TikTakToe class
        TicTacToeApp gameWindow = new TicTacToeApp("TikTakToe Game");
    }

}