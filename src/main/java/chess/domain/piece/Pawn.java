package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;
import java.util.stream.Collectors;

public final class Pawn extends Piece {

	private static final int PAWN_SCORE = 1;
	private static final int MOVE_TWICE = 2;
	private static final String symbol = "pawn";

	public Pawn(final Team team) {
		super(team, symbol);
	}

	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		List<Direction> directions = Direction.getPawnDirection(team, !targetPiece.isBlank());
		List<Position> movablePositions = getMovablePositions(source, directions);
		if (!movablePositions.contains(target)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	private List<Position> getMovablePositions(final Position source, final List<Direction> directions) {
		List<Position> movablePositions = source.getArrivalPositionsByDirections(directions);
		if (source.isInitialPawnRank(team)) {
			List<Position> twiceMovedPositions = moveTwice(source, directions);
			movablePositions.addAll(twiceMovedPositions);
		}
		return movablePositions;
	}

	private List<Position> moveTwice(final Position source, final List<Direction> directions) {
		return directions.stream()
				.map(direction -> source.addDirection(direction, MOVE_TWICE))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isBlank() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}
}
