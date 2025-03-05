import java.util.Scanner;

public class test {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'R';
    private static final char PLAYER_TWO = 'Y';
    private static char[][] board = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();
        boolean isPlayerOneTurn = true;
        boolean gameWon = false;
        
        while (!gameWon && !isBoardFull()) {
            printBoard();
            char currentPlayer = isPlayerOneTurn ? PLAYER_ONE : PLAYER_TWO;
            System.out.println("Player " + currentPlayer + "'s turn. Choose a column (0-6): ");
            int column = scanner.nextInt();
            
            if (column < 0 || column >= COLUMNS || !isValidMove(column)) {
                System.out.println("Invalid move, try again.");
                continue;
            }
            
            int row = dropPiece(column, currentPlayer);
            if (checkWin(row, column, currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameWon = true;
            }
            isPlayerOneTurn = !isPlayerOneTurn;
        }
        
        if (!gameWon) {
            printBoard();
            System.out.println("It's a draw!");
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    private static boolean isValidMove(int column) {
        return board[0][column] == EMPTY;
    }

    private static int dropPiece(int column, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY) {
                board[i][column] = player;
                return i;
            }
        }
        return -1;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < COLUMNS; i++) {
            if (board[0][i] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWin(int row, int col, char player) {
        return checkDirection(row, col, player, 1, 0) || // Vertical
               checkDirection(row, col, player, 0, 1) || // Horizontal
               checkDirection(row, col, player, 1, 1) || // Diagonal /
               checkDirection(row, col, player, 1, -1);  // Diagonal \
    }

    private static boolean checkDirection(int row, int col, char player, int rowDir, int colDir) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }
}
