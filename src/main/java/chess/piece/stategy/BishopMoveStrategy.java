package chess.piece.stategy;

import chess.board.Location;

public class BishopMoveStrategy extends MoveStrategy {
	@Override
	void checkRange(Location now, Location destination) {
		if (!now.isDiagonal(destination)) {
			throw new IllegalArgumentException("비숍이 잘못된 범위의 경로를 입력했습니다.");
		}
	}

	@Override
	void checkStrategy(Location now, Location destination, boolean destinationHasEnemy) {

	}
}
