package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.service.ChessService;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		Board initial = BoardFactory.create();
		Team first = Team.WHITE;
		ChessService chessService = ChessService.of(initial);

		ChessController controller = new ChessController(chessService, initial, first);

		controller.start();
		while (true) {
			controller.playTurn();
		}
	}
}
