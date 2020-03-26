package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public class MovePatternFactory {
	private static final int ONE_POINT = 1;
	private static final int TWO_POINT = 2;

	public static MovePattern findMovePattern(Position source, Position target) {
		if (source.isSameRank(target) || source.isSameFile(target)) {
			return new StraightPattern(source, target);
		}
		if (Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target))) {
			return new CrossPattern(source, target);
		}
		if ((Math.abs(source.calculateFileDistance(target)) == ONE_POINT && Math.abs(source.calculateRankDistance(target)) == TWO_POINT)
				|| (Math.abs(source.calculateRankDistance(target)) == ONE_POINT && Math.abs(source.calculateFileDistance(target)) == TWO_POINT)) {
			return new KnightPattern(target);
		}
		return null;
	}
}
