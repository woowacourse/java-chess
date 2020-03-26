package chess.domain.movetype;

import chess.domain.chessPiece.position.Position;

public class KnightType implements MoveType {
	private static final String ERROR_MESSAGE_UNSUPPORT_METHOD = "지원하지 않는 메소드 입니다";

	private final Position targetPosition;

	public KnightType(Position targetPosition) {
		this.targetPosition = targetPosition;
	}

	@Override
	public Direction getDirection() {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORT_METHOD);
	}

	@Override
	public int getCount() {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORT_METHOD);
	}

	public Position getTargetPosition() {
		return targetPosition;
	}
}
