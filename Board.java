import java.util.ArrayList;

public class Board {
    private Integer[][] boardArray;
    private int rows;
    private int cols;
    private int blanks; // Number of blank squares remained
    private Integer[] lastRandomMove;
    /* Maybe have 2 arraylists mapping available blank rows and cols here (updated when moved) 
     * so that moveRandom() doesn't have to go through the whole board every time
    */
    

    public Board(int rows, int cols) {
        boardArray = new Integer[rows][cols];
        this.rows = rows;
        this.cols = cols;
        lastRandomMove = new Integer[2];
        
        // Initialize blank state
        blanks = rows * cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardArray[i][j] = 0;
                // States: -1 = blocked, 0 = blank, 1 = player 1, 2 = player 2 
            }
        }
    }

    public Integer[][] getArray() {
        return boardArray;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Integer[] getLastRandMove() {
        return lastRandomMove;
    }

    
    public boolean isFinished() {
    /* Check if game is finished (board is fully filled) */
        if (blanks == 0) {
            return true;
        }

        return false;
    }

    public class Player {
    /* Players that are playing the board */
        private int playerOrder; // 1 or 2
        private char playerType; // h or c - human/computer

        public Player(int playerOrder, char playerType) {
            this.playerOrder = playerOrder;
            this.playerType = playerType;
        }

        public char getType() {
            return playerType;
        }

        public int getOrder() {
            return playerOrder;
        }

        public int move(int row, int col) throws IndexOutOfBoundsException {
        /* 0 = success, 1 = board finished, 2 = square not blank */
            if (isFinished()) {
                return 1;
            }
            
            if (boardArray[row][col] != 0) {
                return 2;
            }

            // Fill chosen position with player sign
            boardArray[row][col] = playerOrder;
            blanks -= 1;

            for (int i = row-1; i <= row+1; i++) {
                // Next iteration if out of bound
                if (i < 0 || i >= rows) {
                    continue;
                }
                for (int j = col-1; j <= col+1; j++) {
                    // Next iteration if out of bound
                    if (j < 0 || j >= cols) {
                        continue;
                    }
                    // Change adjacent squares to "blocked"
                    if (boardArray[i][j] == 0) {
                        boardArray[i][j] = -1;
                        blanks -= 1;    
                    }
                }
            }

            return 0;
        }

        public int moveRandom() {
        /* 0 = success, 1 = board finished, */    
            if (isFinished()) {
                return 1;
            }
            
            // First map all blank positions to 2 arraylists
            ArrayList<Integer> blankRows = new ArrayList<Integer>();
            ArrayList<Integer> blankCols = new ArrayList<Integer>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (boardArray[i][j] == 0) {
                        blankRows.add(i);
                        blankCols.add(j);
                    }
                }
            }
            
            // Get a random position from above
            int max = blankRows.size() - 1;
            int rand = (int) ((Math.random() * (max)));
            move(blankRows.get(rand), blankCols.get(rand));

            // Record move
            lastRandomMove[0] = blankRows.get(rand);
            lastRandomMove[1] = blankCols.get(rand);
            
            return 0;
        }

    }
}
