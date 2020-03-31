package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.position.Position;

public class Bishop extends Piece {
	public Bishop(Position position, Team team) {
		super(position, Name.BISHOP, team);
	}

	@Override
	public boolean canNotMoveTo(Piece that) {
		return isSameTeam(that.team) || !createMovableArea().contains(that.position);
	}

	@Override
	protected List<Position> createMovableArea() {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(this.position))
			.filter(this::isDiagonal)
			.collect(Collectors.toList());
	}

	private boolean isDiagonal(Position position) {
		return Math.abs(position.getColumnGap(this.position)) == Math.abs(position.getRowGap(this.position));
	}

	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public boolean hasToAlive() {
		return false;
	}

	@Override
	public boolean isPenaltyApplier() {
		return false;
	}
}
