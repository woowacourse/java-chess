import chess.GameManager;
import chess.PieceFactory;
import chess.board.Location;
import view.InputView;
import view.OutputView;

public class ChessController {
	public static void main(String[] args) {
		String command = "";
		OutputView.printInformation();
		GameManager gameManager = new GameManager(PieceFactory.createPieces());

		while (gameManager.isRunning()) {
			command = InputView.inputStartCommand();
			start(command, gameManager);
			movePiece(command, gameManager);
			status(command, gameManager);
			if (command.equals("end")) {
				break;
			}
		}
		OutputView.printStatus(gameManager);
	}

	private static void status(String command, GameManager gameManager) {
		if (command.equals("status")) {
			OutputView.printStatus(gameManager);
		}
	}

	private static void start(String command, GameManager gameManager) {
		if (command.equals("start")) {
			OutputView.printBoard(gameManager);
		}
	}

	private static void movePiece(String command, GameManager gameManager) {
		if (command.matches("move [a-h][1-8] [a-h][1-8]")) {
			char nowCol = command.split(" ")[1].charAt(0);
			int nowRow = command.split(" ")[1].charAt(1) - '0';
			char destCol = command.split(" ")[2].charAt(0);
			int destRow = command.split(" ")[2].charAt(1) - '0';

			Location now = new Location(nowRow, nowCol);
			Location destination = new Location(destRow, destCol);

			gameManager.movePiece(now, destination);
			OutputView.printBoard(gameManager);
		}
	}
}
