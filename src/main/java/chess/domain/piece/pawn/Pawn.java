package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Pawn extends Piece {
	public Pawn(Position position) {
		super(position, "p", new PawnMoveStrategy());
	}
}
