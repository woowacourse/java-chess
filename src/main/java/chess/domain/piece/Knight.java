package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.KnightDirectionStrategy;

public class Knight extends Piece {

	private static final String INVALID_DIRECTION_KNIGHT = "Knight갈 수 없는 방향입니다.";

	private final String symbol;

	private Knight(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Knight createWhite() {
		return new Knight(Color.WHITE, "♞");
	}

	public static Knight createBlack() {
		return new Knight(Color.BLACK, "♘");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
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
}
