package chess;

import java.util.Map;

import chess.piece.Piece;
import chess.position.Position;

public class Board {
	private final Map<Position, Piece> pieces;

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}
}
