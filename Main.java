import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Obstruction! ");
        Board board = new Board(20, 20);
        Display.printBoard(board);
    }
}