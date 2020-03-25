package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.BishopMovable;

public class Bishop extends Piece {
	public Bishop(Position position, String name) {
		super(position, name, new BishopMovable());
	}
}
