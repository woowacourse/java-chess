package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Position;

public class Pawn extends Piece {

	public static final List<Direction> LOWER_TEAM_POSSIBLE_DIRECTIONS = Arrays.asList(Direction.NORTH,
		Direction.NORTHNORTH, Direction.NORTHEAST, Direction.NORTHWEST);
	public static final List<Direction> UPPER_TEAM_POSSIBLE_DIRECTIONS = Arrays.asList(Direction.SOUTH,
		Direction.SOUTHSOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST);

	public Pawn(Position position, Team team) {
		super(position, team);
		this.representation = 'P';
	}

	@Override
	public void validateMove(Position destination) {
		if (this.team == Team.WHITE)
			validateMoveByDirection(destination, LOWER_TEAM_POSSIBLE_DIRECTIONS);
		if (this.team == Team.BLACK)
			validateMoveByDirection(destination, UPPER_TEAM_POSSIBLE_DIRECTIONS);
	}

	private void validateMoveByDirection(Position destination, List<Direction> possibleDirections) {
		if (!possibleDirections.contains(this.position.calculateDirection(destination))) {
			throw new IllegalArgumentException("말이 움직일 수 없는 자리입니다.");
		}
	}

}
