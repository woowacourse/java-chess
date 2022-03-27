package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.RookDirectionStrategy;

public class Rook extends Piece {

	private static final String INVALID_DIRECTION_ROOK = "Rook이 갈 수 없는 방향입니다.";
	private static final double ROOK_SCORE = 5.0;

	private final String symbol;

	private Rook(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Rook createWhite() {
		return new Rook(Color.WHITE, "♜");
	}

	public static Rook createBlack() {
		return new Rook(Color.BLACK, "♖");
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = new RookDirectionStrategy().find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_ROOK);
		}
		return direction.get();
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
		return ROOK_SCORE;
	}
}
