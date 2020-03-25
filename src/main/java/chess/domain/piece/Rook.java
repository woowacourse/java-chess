package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.RookMovable;

public class Rook extends Piece {
	public Rook(Position position, String name) {
		super(position, name, new RookMovable());
	}
}
