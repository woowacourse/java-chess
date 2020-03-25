package chess.domain.piece.queen;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Queen extends Piece {
	public Queen(Position position) {
		super(position, new QueenMoveStrategy());
	}
}
