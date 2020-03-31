package chess.piece.stategy;

import java.util.List;

import chess.board.Location;
import chess.team.Team;

public class KingMoveStrategy extends MoveStrategy {
	private final List<Direction> kingDirection = Direction.EVERY_DIRECTION;

	public KingMoveStrategy(Team team) {
		super(team);
	}

	@Override
	void checkRange(Location now, Location destination) {
		if (!now.validOneSpaceRange(destination, kingDirection, 1)) {
			throw new IllegalArgumentException("유효하지 않는 범위를 입력했습니다.");
		}
	}

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {

	}
}
