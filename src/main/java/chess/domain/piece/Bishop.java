package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.movable.BishopMovable;


public class Bishop extends Piece {
	public Bishop(Position position, String name, Color color) {
		super(position, name, new BishopMovable(), color, 3);
	}
}
