package chess.controller;

import chess.domain.Position;

public class PositionDto {

	private final Position sourcePosition;
	private final Position targetPosition;

	public PositionDto(final Position sourcePosition, final Position targetPosition) {
		this.sourcePosition = sourcePosition;
		this.targetPosition = targetPosition;
	}

	public Position getSourcePosition() {
		return sourcePosition;
	}

	public Position getTargetPosition() {
		return targetPosition;
	}
}
