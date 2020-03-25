package chess.domain.piece.bishop;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Bishop extends Piece {
	protected Bishop(Position position) {
		super(position, new BishopMoveStrategy());
	}
}
