public abstract class Display {
    /* Use default constructor */

    public static void printBoard(Board board) {
        Integer[][] boardArray = board.getArray();
        
        // Column numbering
        System.out.print("   "); // Triple-space
        for (int j = 0; j < board.getCols(); j++) {
            if (j < 9) {
                System.out.print(" " + (j+1) + " ");
            }
            else {
                System.out.print((j+1) + " ");
            }
        }
        System.out.println();
        
        for (int i = 0; i < board.getRows(); i++) {
            // Row numbering
            if (i < 9) {
                System.out.print(" ");
                System.out.print(i+1);
                System.out.print(" ");
            }
            else {
                System.out.print(i+1);
                System.out.print(" ");
            }
            
            // Main body
            for (int j = 0; j < board.getCols(); j++) {
                if (boardArray[i][j] == null) {
                    System.out.print("[#]");
                }
                
                switch (boardArray[i][j]) {
                    case 0:
                        System.out.print("[ ]");
                        break;
                    case 1:
                        System.out.print("[X]");
                        break;
                    case 2: 
                        System.out.print("[O]");
                        break;
                }
            }
            System.out.println();
        }
    }
}
