package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.movable.QueenMovable;

public class Queen extends Piece {
	public Queen(Position position, String name, Color color) {
		super(position, name, new QueenMovable(), color, 9);
	}
}
