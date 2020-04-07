package chess;

import chess.controller.ChessController;
import chess.controller.ConsoleChessController;
import chess.dao.BoardDAO;
import chess.dao.TurnInfoDAO;
import chess.service.ChessService;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		ChessService chessService = new ChessService(new BoardDAO(), new TurnInfoDAO());
		ChessController controller = new ConsoleChessController(chessService);

		controller.start();
		while (true) {
			controller.playTurn();
		}
	}
}
