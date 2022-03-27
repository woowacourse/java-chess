package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.KnightDirectionStrategy;

public class Knight extends Piece {

	private static final String INVALID_DIRECTION_KNIGHT = "Knight가 갈 수 없는 방향입니다.";
	private static final double KNIGHT_SCORE = 2.5;

	private static final Knight whiteKing = new Knight(Color.WHITE);
	private static final Knight blackKing = new Knight(Color.BLACK);

	private Knight(Color color) {
		super(color);
	}

	public static Knight createWhite() {
		return whiteKing;
	}

	public static Knight createBlack() {
		return blackKing;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = new KnightDirectionStrategy().find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_KNIGHT);
		}
		return direction.get();
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return KNIGHT_SCORE;
	}
}
