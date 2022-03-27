package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.KingDirectionStrategy;

public class King extends Piece {

	private static final String INVALID_DIRECTION_KING = "King이 갈 수 없는 방향입니다.";
	private static final String INVALID_DISTANCE_KING = "King이 갈 수 없는 거리입니다.";
	private static final double KING_SCORE = 0;

	private final String symbol;

	private King(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static King createWhite() {
		return new King(Color.WHITE, "♚");
	}

	public static King createBlack() {
		return new King(Color.BLACK, "♔");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public boolean isPawn() {
		return false;
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
		if (from.canReach(to, direction.getUnitPosition(), 1)) {
			return direction;
		}
		throw new IllegalArgumentException(INVALID_DISTANCE_KING);
	}
}
