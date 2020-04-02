package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.controller.WebChessController;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.service.ChessService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/public");

		Board initial = BoardFactory.create();
		Team first = Team.WHITE;
		ChessService chessService = ChessService.of(initial);

		ChessController controller = new WebChessController(chessService, initial, first);

		controller.start();
		controller.playTurn();
	}
}
