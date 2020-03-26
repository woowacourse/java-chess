package chess.domain.MovableStrategy;

import chess.domain.position.Position;

public class KnightMovableStrategy implements MovableStrategy {
	@Override
	public boolean canMove(Position source, Position target) {
		int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
		int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

		return isNotExistOnAxis(fileInterval, rankInterval) && isKnightRange(fileInterval, rankInterval);
	}

	private boolean isNotExistOnAxis(int fileInterval, int rankInterval) {
		return fileInterval != 0 && rankInterval != 0;
	}

	private boolean isKnightRange(int fileInterval, int rankInterval) {
		return fileInterval + rankInterval == 3;
	}

	@Override
	public boolean canLeap(Position source, Position target) {
		return true;
	}
}
