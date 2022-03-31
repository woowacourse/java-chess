package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.RoyalDirectionStrategy;
import chess.domain.position.Position;

public class King extends Piece {

	private static final double KING_SCORE = 0;
	private static final int KING_MAX_DISTANCE = 1;

	private static final King whiteKing = new King(Color.WHITE);
	private static final King blackKing = new King(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private King(Color color) {
		super(color);
		directionStrategy = new RoyalDirectionStrategy();
	}

	public static King createWhite() {
		return whiteKing;
	}

	public static King createBlack() {
		return blackKing;
	}

	@Override
	public Direction getMovableDirection(Position from, Position to) {
		return directionStrategy.find(from, to);
	}

	@Override
	public boolean isMovable(Position from, Position to) {
		Direction direction;
		try {
			direction = getMovableDirection(from, to);
		} catch (IllegalArgumentException exception) {
			return false;
		}
		return from.canReach(to, direction.getUnitPosition(), KING_MAX_DISTANCE);
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
}
