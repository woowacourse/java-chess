package chess.piece.stategy;

import java.util.List;

import chess.board.Location;
import chess.team.Team;

public class KnightMoveStrategy extends MoveStrategy {
	private final List<Direction> knightDirection = Direction.KNIGHT_DIRECTION;

	public KnightMoveStrategy(Team team) {
		super(team);
	}

	@Override
	void checkRange(Location now, Location destination) {
		if (!now.validOneSpaceRange(destination, knightDirection, 1)) {
			throw new IllegalArgumentException("유효하지 않는 범위를 입력했습니다.");
		}
	}

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {
	}
}
