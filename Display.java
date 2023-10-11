public abstract class Display {
    public static void printBoard(Board board) {
    /* Print the latest state of the board */    
        Integer[][] boardArray = board.getArray();
        
        // Column numbering
        System.out.print("   "); // Triple-space
        for (int j = 0; j < board.getCols(); j++) {
            if (j < 10) {
                System.out.print(" " + j + " ");
            }
            else {
                System.out.print(j + " ");
            }
        }
        System.out.println();
        
        for (int i = 0; i < board.getRows(); i++) {
            // Row numbering
            if (i < 10) {
                System.out.print(" ");
                System.out.print(i);
                System.out.print(" ");
            }
            else {
                System.out.print(i);
                System.out.print(" ");
            }
            
            // Main body
            for (int j = 0; j < board.getCols(); j++) {
                if (boardArray[i][j] == -1) {
                    System.out.print("[#]");
                }
                else if (boardArray[i][j] == 0) {
                    System.out.print("[ ]");
                }
                else if (boardArray[i][j] == 1) {
                    System.out.print("[O]");
                }
                else if (boardArray[i][j] == 2) {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }
    }

    /* Print methods that symply pass arguments to their System.out versions */
    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printf(String message, Object ... args) {
        System.out.printf(message, args);
    }
}
