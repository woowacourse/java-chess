package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.Direction;
import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class Pawn extends Piece {
	private static final String UNMOVABLE_DESTINATION_FOR_PAWN = "폰이 이동할 수 없는 위치입니다.";

	private static final List<Direction> LOWER_TEAM_POSSIBLE_DIRECTIONS = Arrays.asList(Direction.NORTH,
		Direction.NORTHNORTH, Direction.NORTHEAST, Direction.NORTHWEST);
	private static final List<Direction> UPPER_TEAM_POSSIBLE_DIRECTIONS = Arrays.asList(Direction.SOUTH,
		Direction.SOUTHSOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST);

	public Pawn(Position position, Team team) {
		super(position, team);
		this.representation = 'P';
		this.score = 1;
	}

	@Override
	protected void validateMove(Position destination) {
		if (this.team == Team.WHITE)
			validateMoveByDirection(destination, LOWER_TEAM_POSSIBLE_DIRECTIONS);
		if (this.team == Team.BLACK)
			validateMoveByDirection(destination, UPPER_TEAM_POSSIBLE_DIRECTIONS);
	}

	private void validateMoveByDirection(Position destination, List<Direction> possibleDirections) {
		Direction direction = this.position.calculateDirection(destination);
		if (!this.position.isPawnInOriginalPosition() && direction.isForwardDouble()) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
		if (!possibleDirections.contains(direction)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}

	@Override
	public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
		Direction direction = this.position.calculateDirection(destination);
		if ((direction.isForwardForPawn() && destinationPiece != null) || (direction.isDiagonal() && destinationPiece == null)) {
			throw new IllegalMoveException(UNMOVABLE_DESTINATION_FOR_PAWN);
		}
		validateNoObstacle(piecesInBetween);
	}

	public boolean isInSameColumn(Pawn pawn) {
		return this.position.isInSameColumn(pawn.position);
	}

	@Override
	public double getScore(List<Piece> pieces) {
		List<Pawn> pawns = pieces.stream()
				.filter(p -> p instanceof Pawn)
				.map(p -> (Pawn)p)
				.collect(Collectors.toList());
			if (pawns.stream()
					.anyMatch(p -> !p.equals(this) && this.isInSameColumn(p))) {
				return this.score / 2;
			}
		return this.score;
	}
}
