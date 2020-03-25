package chess;

import java.util.Map;

import chess.piece.Piece;
import chess.position.Position;

public class Board {
	private final Map<Position, Piece> pieces;

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public Piece getPiece(Position position) {
		return pieces.get(position);
	}

	public void move(Position from, Position to) {
		Piece target = pieces.remove(from);
        pieces.put(to, target);
    }
}
