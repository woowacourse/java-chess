package chess.domain.piece;

import chess.domain.piece.movable.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.movable.RookMovable;

import java.util.List;

public class Rook extends Piece {
	private final List<Direction> directions = Direction.linearDirection();

	public Rook(Position position, String name, Color color) {
		super(position, name, new RookMovable(), color, 5);
	}
}
