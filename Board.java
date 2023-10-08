public class Board {
    private Integer[][] boardArray;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        boardArray = new Integer[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardArray[i][j] = 0;
                // States: null = blocked, 0 = blank, 1 = player 1, 2 = player 2 
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
}
