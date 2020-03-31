package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.position.Position;

public class Rook extends Piece {
	public Rook(Position position, Team team) {
		super(position, Name.ROOK, team);
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
			.filter(this::isCross)
			.collect(Collectors.toList());
	}

	private boolean isCross(Position position) {
		return this.position.isColumnEquals(position) || this.position.isRowEquals(position);
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
