package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.movable.RookMovable;

public class Rook extends Piece {
	public Rook(Position position, String name, Color color) {
		super(position, name, new RookMovable(), color, 5);
	}
}
