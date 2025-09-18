package tickTacToeGame;

import java.util.Scanner;

public class TicTacToe {
	
	//properties to be used
    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;
    
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        initializeBoard();
    }
    
    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    // Display the current board
    public void displayBoard() {
        System.out.println("\n   0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----------");
        }
        System.out.println();
    }
    
    // Make a move on the board
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }
    
    // Check if there's a winner
    public boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        
        return false;
    }
    
    // Check if the board is full (draw)
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Switch to the other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    // Get player input
    private int[] getPlayerMove() {
        int row, col;
        while (true) {
            try {
                System.out.print("Player " + currentPlayer + ", enter your move (row col): ");
                row = scanner.nextInt();
                col = scanner.nextInt();
                
                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid input! Please enter row and column between 0 and 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter two integers.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
    
    // Main game loop
    public void playGame() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Enter your moves as row and column numbers (0-2).");
        
        while (true) {
            displayBoard();
            
            int[] move = getPlayerMove();
            int row = move[0];
            int col = move[1];
            
            if (makeMove(row, col)) {
                if (checkWinner()) {
                    displayBoard();
                    System.out.println("🎉 Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    break;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move! That position is already taken or out of bounds.");
            }
        }
        
        System.out.print("Would you like to play again? (y/n): ");
        String playAgain = scanner.next();
        if (playAgain.toLowerCase().startsWith("y")) {
            resetGame();
            playGame();
        } else {
            System.out.println("Thanks for playing!");
            scanner.close();
        }
    }
    
    // Reset the game for a new round
    private void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
    }
    
    // Main method to start the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
} 
