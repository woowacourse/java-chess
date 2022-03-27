package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.KingDirectionStrategy;

public class King extends Piece {

	private static final String INVALID_DIRECTION_KING = "King이 갈 수 없는 방향입니다.";
	private static final String INVALID_DISTANCE_KING = "King이 갈 수 없는 거리입니다.";

	private static final double KING_SCORE = 0;
	private static final int KING_MAX_DISTANCE = 1;

	private static final King whiteKing = new King(Color.WHITE);
	private static final King blackKing = new King(Color.BLACK);

	private King(Color color) {
		super(color);
	}

	public static King createWhite() {
		return whiteKing;
	}

	public static King createBlack() {
		return blackKing;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		return true;
	}

	@Override
	public double getScore() {
		return KING_SCORE;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> findDirection = new KingDirectionStrategy().find(from, to);
		if (findDirection.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_KING);
		}
		Direction direction = findDirection.get();
		if (from.canReach(to, direction.getUnitPosition(), KING_MAX_DISTANCE)) {
			return direction;
		}
		throw new IllegalArgumentException(INVALID_DISTANCE_KING);
	}
}
