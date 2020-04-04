package chess.domain;

import java.util.List;

import chess.domain.position.Position;
import chess.domain.position.PositionGap;
import chess.domain.position.Positions;

public class MoveManager {
	public static final String CANNOT_MOVE_POSITION = "이동할 수 없는 좌표 입니다.";

	private Position nowPosition;

	public MoveManager(Position nowPosition) {
		this.nowPosition = nowPosition;
	}

	public Positions makePath(Position targetPosition, List<Direction> directions) {
		PositionGap positionGap = nowPosition.calculateDiff(targetPosition);
		Direction direction = positionGap.calculateDirection(directions);
		return nowPosition.calculatePath(targetPosition, direction);
	}

	public Direction getMatchDirection(Position targetPosition) {
		PositionGap positionGap = nowPosition.calculateDiff(targetPosition);
		return positionGap.getMatchDirection();
	}

	public void validateMove(Direction direction, List<Direction> directions) {
		if (directions.contains(direction) == false) {
			throw new IllegalArgumentException(CANNOT_MOVE_POSITION);
		}
	}

	public void validateKnightMove(Position targetPosition) {
		PositionGap positionGap = nowPosition.calculateDiff(targetPosition);
		if (positionGap.cannotMoveKnight()) {
			throw new IllegalArgumentException(CANNOT_MOVE_POSITION);
		}
	}

	public void changePosition(Position position) {
		this.nowPosition = position;
	}
}
