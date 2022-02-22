package tictactoe;

import java.util.Scanner;
public class Main {
    //booleans track game state
    public static boolean oWins = false, xWins = false, draw = false;
    //counter keeps track of how many moves have happened in the game
    public static int moveCounter = 0;
    //initialize 3x3 array for gameboard.
    public static char[][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};

    //initialize scanner
    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //printBoard method prints the empty gameboard
        printBoard();
        //while there is no winner, and a draw hasnt happened,
        //continue calling playerMoves()
        while(((!xWins) && (!oWins) && (!draw)))  {
            playerMoves();
        }
    }

    public static void checkForWinner() {

        //check for conditions where X wins
        if (gameBoard[0][0] == 'X') {
            if ((gameBoard[0][1] == 'X' && gameBoard[0][2] == 'X') ||
                    (gameBoard[1][0] == 'X' && gameBoard[2][0] == 'X') ||
                    (gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X')) {
                xWins = true;
            }
        }
        if (gameBoard[1][1] == 'X') {
            if ((gameBoard[0][2] == 'X' && gameBoard[2][0] == 'X') ||
                    (gameBoard[1][0] == 'X' && gameBoard[1][2] == 'X') ||
                    (gameBoard[0][1] == 'X' && gameBoard[2][1] == 'X')) {
                xWins = true;
            }
        }
        if (gameBoard[2][2] == 'X') {
            if ((gameBoard[1][2] == 'X' && gameBoard[0][2] == 'X') ||
                    (gameBoard[2][1] == 'X' && gameBoard[2][0] == 'X'))
                    {
                xWins = true;
            }
        }
        //check for conditions where O wins
        if (gameBoard[0][0] == 'O') {
            if ((gameBoard[0][1] == 'O' && gameBoard[0][2] == 'O') ||
                    (gameBoard[1][0] == 'O' && gameBoard[2][0] == 'O') ||
                    (gameBoard[1][1] == 'O' && gameBoard[2][2] == 'O')) {
                oWins = true;
            }
        }
        if (gameBoard[1][1] == 'O') {
            if ((gameBoard[0][2] == 'O' && gameBoard[2][0] == 'O') ||
                    (gameBoard[1][0] == 'O' && gameBoard[1][2] == 'O') ||
                    (gameBoard[0][1] == 'O' && gameBoard[2][1] == 'O')) {
                oWins = true;
            }
        }
        if (gameBoard[2][2] == 'O') {
            if ((gameBoard[1][2] == 'O' && gameBoard[0][2] == 'O') ||
                    (gameBoard[2][1] == 'O' && gameBoard[2][0] == 'O'))
            {
                oWins = true;
            }
        }
        //***********************************************
        //returns string to declare result of game//
        //**********************************************
        String result = "";
        //if theres no winner, but 9 moves have been made,
        //gameboard will be full so a draw is declared and the game ends
        if((!xWins) &&
                (!oWins) &&
                (moveCounter == 9)) {
            result = "Draw";
            draw = true;
        }
        else if (oWins){
            result = "O wins";
        }
        else if (xWins){
            result = "X wins";
        }
        System.out.println(result);
    }

    public static void playerMoves() {
        //int acts as trigger for when valid input is given.
        int validMove = 0;
        while ((
                //while no valid move has been made
                (validMove == 0) &&
                        //and there are moves left on the board
                        (moveCounter < 9)))
                        {
                            //player makes a move by entering two numbers from 1-3
            System.out.print("Enter the coordinates: ");
            try {
                //input takes two numbers as x,y coordinates,
                //subtracts one to work with array element numbers
                int x = scan.nextInt() - 1;
                int y = scan.nextInt() - 1;
                //if the numbers are out of range,
                //throw a warning and reset
                if (x > 2 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                //if the chosen game square is not empty,
                // throw a warning and reset
                } else if (gameBoard[x][y] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    //X moves first move, and even numbered (2,4,6,8) moves
                    if (moveCounter % 2 == 0) {
                        gameBoard[x][y] = 'X';
                        //O moves on odd numbered moves
                    } else {
                        gameBoard[x][y] = 'O';
                    }
                    // when a move is made by X or O,
                    // valid move is set to one to stop the loop,
                    validMove = 1;
                    // move counter is incremented,
                    moveCounter++;
                    // printBoard() is called to print the current game board,
                    printBoard();
                    // finally checkForWinner() is called to check if game has been won
                    // before all moves have been made.
                    checkForWinner();
                }
                // if the user enters non-number characters,
                // a number format exception will be thrown,
                // scan.nextLine() clears the input stream or the program will infinitely loop
            } catch (Exception NumberFormatException) {
                System.out.println("You should enter numbers!");
                scan.nextLine();
            }
        }
    }

    public static void printBoard() {
        //prints a string which represents the game board like example below
        //  ---------
        //  | O     |
        //  | O X O |
        //  | X X X |
        //  ---------
        String board =
                "---------\n| " +
                        gameBoard[0][0] +
                        " " +
                        gameBoard[0][1] +
                        " " +
                        gameBoard[0][2] +
                        " |\n| " +
                        gameBoard[1][0] +
                        " " +
                        gameBoard[1][1] +
                        " " +
                        gameBoard[1][2] +
                        " |\n| " +
                        gameBoard[2][0] +
                        " " +
                        gameBoard[2][1] +
                        " " +
                        gameBoard[2][2] +
                        " |\n---------";
        System.out.println(board);
    }

}
