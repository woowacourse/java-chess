package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public class KnightPattern implements MovePattern {
	private static final int ONE_POINT = 1;
	private static final int TWO_POINT = 2;
	private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";

	public MovePattern valueOf(Position source, Position target) {
		return new KnightPattern();
	}

	@Override
	public boolean isMatchedPoints(Position source, Position target) {
		return (Math.abs(source.calculateFileDistance(target)) == ONE_POINT
				&& Math.abs(source.calculateRankDistance(target)) == TWO_POINT)
				|| (Math.abs(source.calculateRankDistance(target)) == ONE_POINT
				&& Math.abs(source.calculateFileDistance(target)) == TWO_POINT);
	}

	@Override
	public Direction getDirection() {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
	}

	@Override
	public int getCount() {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
	}
}
