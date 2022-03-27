package chess.domain.piece;

import java.util.Optional;

import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.BishopDirectionStrategy;

public class Bishop extends Piece {

	private static final String INVALID_DIRECTION_BISHOP = "비숍이 갈 수 없는 방향입니다.";
	private static final double BISHOP_SCORE = 3.0;

	private static final Bishop whiteBishop = new Bishop(Color.WHITE);
	private static final Bishop blackBishop = new Bishop(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Bishop(Color color) {
		super(color);
		directionStrategy = new BishopDirectionStrategy();
	}

	public static Bishop createWhite() {
		return whiteBishop;
	}

	public static Bishop createBlack() {
		return blackBishop;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = directionStrategy.find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_BISHOP);
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
		return BISHOP_SCORE;
	}
}
