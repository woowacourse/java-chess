package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.movable.KnightMovable2;

public class Knight extends Piece {
	public Knight(Position position, String name, Color color) {
		super(position, name, new KnightMovable2(), color, 2.5);
	}
}
