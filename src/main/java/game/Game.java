package game;

import java.util.Random;

public class Game {
    private int rows;
    private int columns;
    private char[][] grid;
    private static final char BOARD_SYMBOL = '#';
    private static final char MOVE_SYMBOL = 'X';
    private final int[] startingPosition = new int[2];
    private final int[] possibleMovesOnAxisX = new int[]{-1, 1, -2, 2, 1, -1, 2, -2};
    private final int[] possibleMovesOnAxisY = new int[]{2, 2, 1, 1, -2, -2, -1, -1};
    private static final int NUMBER_OF_POSSIBLE_MOVES = 8;
    private int horizontalMove;
    private int verticalMove;
    private int totalMovesCounter;

    public Game() {
        rows = 8;
        columns = 8;
        grid = new char[rows][columns];
    }

    public Game(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];
    }

    public void go(){
        populateTheGrid();
        setRandomStartingPosition();
        displayBoard();
        System.out.println();
        isMovePossible();
        displayBoard();
        System.out.println();
        printTotalMoves();
    }

    private void populateTheGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = BOARD_SYMBOL;
            }
        }
    }

    private void displayBoard() {
        System.out.print("   ");
        for (int i = 65; i < columns + 65; i++) {
            char letter = (char) i;
            System.out.print(letter + " ");
        }
        for (int i = 1; i <= rows; i++) {
            System.out.println();
            if (i < 10) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(i + " ");
            }
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i - 1][j] + " ");
            }
        }
    }

    private void setRandomStartingPosition() {
        Random random = new Random();
        startingPosition[0] = random.nextInt(NUMBER_OF_POSSIBLE_MOVES);
        startingPosition[1] = random.nextInt(NUMBER_OF_POSSIBLE_MOVES);
        grid[startingPosition[0]][startingPosition[1]] = MOVE_SYMBOL;
    }
    private boolean isMovePossible() {

        for (int i = 0; i < NUMBER_OF_POSSIBLE_MOVES; i++) {
            if (!makeAMoveOnBoard()) {
                return true;
            }
        }
        return false;
    }

    private boolean makeAMoveOnBoard() {
        Random random = new Random();
        int wrongMovesCounter = 0;
        int[] wrongMoves = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};

        while (wrongMovesCounter < NUMBER_OF_POSSIBLE_MOVES) {
            int randomMove = random.nextInt(8);
            horizontalMove = startingPosition[0] + possibleMovesOnAxisX[randomMove];
            verticalMove = startingPosition[1] + possibleMovesOnAxisY[randomMove];

            if (verifyTheNextMove()) {
                grid[horizontalMove][verticalMove] = MOVE_SYMBOL;
                startingPosition[0] = horizontalMove;
                startingPosition[1] = verticalMove;
                totalMovesCounter++;

            } else {
                boolean moveAlreadyMade = false;

                for (int i = 0; i < NUMBER_OF_POSSIBLE_MOVES; i++) {
                    if (randomMove == wrongMoves[i]) {
                        moveAlreadyMade = true;
                    }
                }
                if (!moveAlreadyMade) {
                    wrongMoves[wrongMovesCounter] = randomMove;
                    wrongMovesCounter++;
                }
            }
        }
        return false;
    }

    private boolean verifyTheNextMove() {
        return (horizontalMove < rows && horizontalMove >= 0)
                && (verticalMove < columns && verticalMove >= 0)
                && (grid[horizontalMove][verticalMove] != MOVE_SYMBOL);
    }

    private void printTotalMoves() {
        System.out.println("The knight has moved " + totalMovesCounter + " times on the board.");
    }

}
