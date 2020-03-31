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

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {
		if (now.isDiagonal(destination) && !destinationHasEnemy) {
			throw new IllegalArgumentException("폰의 대각선 이동은 적이 있어야 가능합니다.");
		}
		if (now.isStraight(destination) && destinationHasEnemy) {
			throw new IllegalArgumentException("폰의 직선 이동은 적이 없어야 가능합니다.");
		}
		if (!now.isInitialPawnLocation(team) && now.isTwoSpaceMove(destination)) {
			throw new IllegalArgumentException("폰은 초기위치가 아니면 2칸 전진할 수 없습니다.");
		}
	}
}
