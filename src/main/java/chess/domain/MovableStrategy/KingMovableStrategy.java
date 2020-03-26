package chess.domain.MovableStrategy;

import java.util.Arrays;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

// TODO: 2020/03/26 MovableStrategy 구현하도록
public class KingMovableStrategy extends NonLeapableStrategy {
	public KingMovableStrategy() {
		this.movableDirections.addAll(Arrays.asList(MoveDirection.values()));
	}

	@Override
	boolean canMoveRange(Position source, Position target) {
		int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
		int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

		return fileInterval == 1 || rankInterval == 1;
	}
}
