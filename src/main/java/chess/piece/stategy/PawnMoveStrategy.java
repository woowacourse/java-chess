package chess.piece.stategy;

import java.util.List;

import chess.board.Location;
import chess.team.Team;

public class PawnMoveStrategy extends MoveStrategy {
	private final List<Direction> pawnDirection;

	public PawnMoveStrategy(Team team) {
		super(team);
		pawnDirection = Direction.PAWN_INITIAL_DIRECTION.get(team);
	}

	@Override
	void checkRange(Location now, Location destination) {
		if (!now.validOneSpaceRange(destination, pawnDirection, 1)) {
			throw new IllegalArgumentException("유효하지 않는 범위를 입력했습니다.");
		}
	}

	// todo: 폰의 움직임 + 처음 움직임
	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {
		// 폰이 대각선일때 && 적이 true
		if (isDiagonal() && destinationHasEnemy) {

		}
		// 폰이 직진일때 && 적이 false
		if (isStraight() && !destinationHasEnemy) {

		}
		if(now.isInitialPawnLocation())
	}

	private boolean isStraight() {
		return pawnDirection.contains(Direction.SOUTH)
			|| pawnDirection.contains(Direction.SOUTH_SOUTH)
			|| pawnDirection.contains(Direction.NORTH)
			|| pawnDirection.contains(Direction.NORTH_NORTH);
	}

	private boolean isDiagonal() {
		return !isStraight();
	}
}
