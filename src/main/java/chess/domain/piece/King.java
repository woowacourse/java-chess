package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class King extends Piece {
	private static List<Direction> possibleDirections = Arrays.asList(Direction.NORTH, Direction.EAST, Direction.WEST,
		Direction.SOUTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);

	public King(Position position, Team team) {
		super(position, team);
		this.representation = Arrays.asList('♔', '♚');
		this.score = PieceRule.KING.getScore();
	}

	public void validateMove(Position destination) {
		Direction direction = position.calculateDirection(destination);
		if (!possibleDirections.contains(direction)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}
}
