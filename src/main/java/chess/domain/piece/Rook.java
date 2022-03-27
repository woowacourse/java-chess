package chess.domain.piece;

import java.util.Optional;

import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.RookDirectionStrategy;

public class Rook extends Piece {

	private static final String INVALID_DIRECTION_ROOK = "Rook이 갈 수 없는 방향입니다.";
	private static final double ROOK_SCORE = 5.0;

	private static final Rook whiteRook = new Rook(Color.WHITE);
	private static final Rook blackRook = new Rook(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Rook(Color color) {
		super(color);
		directionStrategy = new RookDirectionStrategy();
	}

	public static Rook createWhite() {
		return whiteRook;
	}

	public static Rook createBlack() {
		return blackRook;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = directionStrategy.find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_ROOK);
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
		return ROOK_SCORE;
	}
}
