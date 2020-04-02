package chess;

import chess.controller.ConsoleChessController;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.service.ChessService;
import chess.view.InputView;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		Board initial = BoardFactory.create();
		Team first = Team.WHITE;
		ChessService chessService = ChessService.of(initial);

		ConsoleChessController controller = new ConsoleChessController(chessService, initial, first);

		controller.start();
		while (true) {
			controller.playTurn(InputView.inputMoveOrStatus());
		}
	}
}
