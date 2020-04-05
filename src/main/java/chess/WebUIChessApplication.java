package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessGame;

public class WebUIChessApplication {

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		ChessGame chessGame = ChessGame.from(chessBoard);
		WebChessController webChessController = new WebChessController(chessGame);

		webChessController.start();
		webChessController.run();
	}

}
