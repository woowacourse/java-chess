package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.KnightMovable;

public class Knight extends Piece {
	public Knight(Position position, String name) {
		super(position, name, new KnightMovable());
	}
}
