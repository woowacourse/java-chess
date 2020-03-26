package chess.domain.movefactory;

import chess.domain.Position;

public class MoveTypeFactory {
	private static final int ONE_POINT = 1;
	private static final int TWO_POINT = 2;

	public static MoveType of(Position source, Position target) {
		if (source.isSameRank(target) || source.isSameFile(target)) {
			return new StraightType(source, target);
		}
		if (Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target))) {
			return new CrossType(source, target);
		}
		if ((Math.abs(source.calculateFileDistance(target)) == ONE_POINT && Math.abs(source.calculateRankDistance(target)) == TWO_POINT)
				|| (Math.abs(source.calculateRankDistance(target)) == ONE_POINT && Math.abs(source.calculateFileDistance(target)) == TWO_POINT)) {
			return new KnightType(target);
		}
		return null;
	}
}
