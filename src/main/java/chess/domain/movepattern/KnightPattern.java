package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public class KnightPattern implements MovePattern {
	private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";

	private final Position targetPosition;

	public KnightPattern(Position targetPosition) {
		this.targetPosition = targetPosition;
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
