package chess;

import chess.controller.ChessController;
import chess.domain.state.Ready;

public class ConsoleApplication {
	public static void main(String[] args) {
		ChessController controller = new ChessController(new ChessGame(new Ready()));
		controller.run();
	}
}
