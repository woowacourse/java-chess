package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.QueenMovable;

public class Queen extends Piece {
	public Queen(Position position, String name) {
		super(position, name, new QueenMovable());
	}
}
