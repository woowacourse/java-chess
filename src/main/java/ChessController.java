import chess.GameManager;
import chess.board.Location;
import chess.piece.PieceFactory;
import view.InputView;
import view.OutputView;

public class ChessController {
	public static void main(String[] args) {
		String command = "";
		OutputView.printInformation();
		GameManager gameManager = new GameManager(new PieceFactory().createPieces());

		while (gameManager.isRunning()) {
			command = InputView.inputStartCommand();
			if ("start".equals(command)) {
				start(gameManager);
			}
			if (command.matches("move [a-h][1-8] [a-h][1-8]")) {
				movePiece(command, gameManager);
			}
			if ("status".equals(command)) {
				status(gameManager);
			}
			if ("end".equals(command)) {
				break;
			}
		}
		OutputView.printStatus(gameManager.createStatistics());
	}

	private static void start(GameManager gameManager) {
		OutputView.printBoard(gameManager);
	}

	private static void status(GameManager gameManager) {
		OutputView.printStatus(gameManager.createStatistics());
	}

	private static void movePiece(String command, GameManager gameManager) {
		String commandNow = command.split(" ")[1];
		String commandDestination = command.split(" ")[2];
		Location now = Location.of(commandNow);
		Location destination = Location.of(commandDestination);

		gameManager.movePiece(now, destination);
		OutputView.printBoard(gameManager);
	}
}
