package chess.domain;

import java.util.List;

import chess.domain.chessBoard.ChessBoard;
import chess.util.ChessBoardRenderer;

public class ChessGame {

	private final ChessBoard chessBoard;
	private final ChessTurn chessTurn;

	public ChessGame(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public void move(List<String> arguments) {

	}

	public void status(List<String> arguments) {
	}

	public boolean isEndState() {
	}

	public boolean isKingCaught() {
	}

	public String getCurrentTurnPieceColor() {
		return null;
	}

	public List<String> getRenderedChessBoard() {
		return ChessBoardRenderer.render(chessBoard);
	}
}
