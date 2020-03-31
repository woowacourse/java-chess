package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.position.Position;

public class Knight extends Piece {
	private static final List<Integer> KNIGHT_MOVE_POSITIONS = List.of(1, 2);

	public Knight(Position position, Team team) {
		super(position, Name.KNIGHT, team);
	}

	@Override
	public boolean canNotMoveTo(Piece that) {
		return isSameTeam(that.team) || !createMovableArea().contains(that.position);
	}

	@Override
	protected List<Position> createMovableArea() {
		return Position.getPositions()
			.stream()
			.filter(this::isKnight)
			.collect(Collectors.toList());
	}

	private boolean isKnight(Position position) {
		List<Integer> positionGap = List.of(
			Math.abs(position.getColumnGap(this.position)),
			Math.abs(position.getRowGap(this.position))
		);

		return positionGap.containsAll(KNIGHT_MOVE_POSITIONS);
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
