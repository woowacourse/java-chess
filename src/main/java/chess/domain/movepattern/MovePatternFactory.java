package chess.domain.movepattern;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.position.Position;

public class MovePatternFactory {
	private static final int ONE_POINT = 1;
	private static final int TWO_POINT = 2;

	public static MovePattern findMovePattern(Position source, Position target) {
		if (isCrossPattern(source, target)) {
			return new CrossPattern(source, target);
		}
		if (isStraightPattern(source, target)) {
			return new StraightPattern(source, target);
		}
		if (isKnightPattern(source, target)) {
			return new KnightPattern();
		}
		throw new IllegalArgumentException(Piece.ERROR_MESSAGE_NOT_MOVABLE);
	}

	public static boolean isCrossPattern(Position source, Position target) {
		return Math.abs(source.calculateRankDistance(target))
				== Math.abs(source.calculateFileDistance(target));
	}

	public static boolean isKnightPattern(Position source, Position target) {
		return (Math.abs(source.calculateFileDistance(target)) == ONE_POINT
				&& Math.abs(source.calculateRankDistance(target)) == TWO_POINT)
				|| (Math.abs(source.calculateRankDistance(target)) == ONE_POINT
				&& Math.abs(source.calculateFileDistance(target)) == TWO_POINT);
	}

	public static boolean isStraightPattern(Position source, Position target) {
		return source.isSameRank(target) || source.isSameFile(target);
	}
}
