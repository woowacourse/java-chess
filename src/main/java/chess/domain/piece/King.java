package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.position.Position;

public class King extends Piece {
	private static final int AROUND_BOUND = 1;

	public King(Position position, Team team) {
		super(position, Name.KING, team);
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
			.filter(this::isAround)
			.collect(Collectors.toList());
	}

	private boolean isAround(Position position) {
		return Math.abs(position.getColumnGap(this.position)) <= AROUND_BOUND &&
			Math.abs(position.getRowGap(this.position)) <= AROUND_BOUND;
	}

	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public boolean hasToAlive() {
		return true;
	}

	@Override
	public boolean isPenaltyApplier() {
		return false;
	}
}