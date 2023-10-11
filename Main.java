import java.io.*;
import javax.management.openmbean.KeyAlreadyExistsException;

class Main {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String input = "";
	
	public static void main(String[] args) {
		Main main = new Main();

		// Get board size from input
		Display.print("Welcome to Obstruction! Please enter a number N\n" +
							"between 6 to 20 to generate a N x N board: ");
		int size = main.getBoardSize();
		Board board = new Board(size, size);
		Display.printf("A %d X %d board is created.\n", size, size);
		
		// Get player order and generate players
		Display.print("Do you want to go first? (y/n) ");
		Board.Player[] players = new Board.Player[2];
		if (main.userGoFirst()) {
			players[0] = board.new Player(1, 'h');
			players[1] = board.new Player(2, 'c');
		}
		else {
			players[0] = board.new Player(1, 'c');
			players[1] = board.new Player(2, 'h');
		}

		Display.println("Game start!");
		Display.printBoard(board);
		
		char winner;
		
		playRounds: // Loop label
		while (true) {
			for (Board.Player player : players) {
				if (player.getType() == 'h') {
					Display.print("Your turn. Please enter a position to move (row-column): ");
				}
				
				main.getMove(player);
				// GetMove() will only return true as we check isFinished() after every move 
				Display.printBoard(board);

				if (player.getType() == 'c') {
					Display.printf("Last move: %d-%d\n", 
										board.getLastRandMove()[0], 
										board.getLastRandMove()[1]);
				}

				if (board.isFinished()) {
					winner = player.getType();
					break playRounds;
				}
			}
		}

		if (winner == 'h') {
			Display.println("You win!");
		}
		else {
			Display.println("Computer wins, you lose!");
		}
	}

	public int getBoardSize() {
		int size = 0;
		while (true) {
			try {
				input = reader.readLine();
				checkUserExit();
				
				size = Integer.parseInt(input);
				if (size < 6 || size > 20) {
					throw new NumberFormatException();
				}
			}
			catch (IOException e) {
				Display.print("Something wrong happened! Exiting program...");
				System.exit(1);
			}
			catch (NumberFormatException e) {
				Display.print("Invalid input! Please try again: ");
				continue;
			}
					
			return size;
		}
	}

	public boolean userGoFirst() {
		while (true) {
			try {
				input = reader.readLine();
				checkUserExit();
				
				if (!input.toLowerCase().equals("y") && 
					!input.toLowerCase().equals("n")) {
					throw new IllegalArgumentException(); 
				}
			}
			catch (IOException e) {
				Display.print("Something wrong happened! Exiting program...");
				System.exit(1);
			}
			catch (IllegalArgumentException e) { 
				Display.print("Invalid input! Please try again: ");
				continue;
			}
					
			if (input.equals("y")) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public boolean getMove(Board.Player player) {
	/* True = success, false = board finished */
		int success = 0;
		if (player.getType() == 'c') {
			success = player.moveRandom();
		}
		else {
			while (true) {
				try {
					input = reader.readLine();
					checkUserExit();

					String[] rowCol = input.split("-");
					if (rowCol.length != 2) {
						throw new IllegalArgumentException();
					}

					int row = Integer.parseInt(rowCol[0]);
					int col = Integer.parseInt(rowCol[1]);
					
					success = player.move(row, col);
					if (success == 2) {
					// success can only be 0 or 1 after this
						throw new KeyAlreadyExistsException();
					}
				}
				catch (IOException e) {
					Display.print("Something wrong happened! Exiting program...");
					System.exit(1);
				}
				catch (KeyAlreadyExistsException e) {
				// Subclass of IllegalArgumentException
					Display.print("Cannot go there! Please try again: ");
					continue;
				}
				catch (IllegalArgumentException e) {
				// Also catch NumberFormatException
					Display.print("Invalid input! Please try again: ");
					continue;
				}
				catch (IndexOutOfBoundsException e) {
				// From Player.move() method
					Display.print("Invalid input! Please try again: ");
					continue;
				}

				break;
			}
		}

		if (success == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void checkUserExit() {
	/* Exit on user demand */
		if (input == null) {
			System.exit(0);
		}
	}
}