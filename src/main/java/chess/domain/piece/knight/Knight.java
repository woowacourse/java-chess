package chess.domain.piece.knight;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Knight extends Piece {
	protected Knight(Position position) {
		super(position, new KnightMoveStrategy());
	}
}
