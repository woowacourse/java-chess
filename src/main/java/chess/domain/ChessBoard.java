package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.Position;
import chess.domain.chesspiece.ChessPiece;

public class ChessBoard {
	private Map<Position, ChessPiece> chessBoard;

	public ChessBoard(Map<Position, ChessPiece> chessBoard) {
		this.chessBoard = new HashMap<>();

	}

}
