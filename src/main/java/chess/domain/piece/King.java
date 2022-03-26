package chess.domain.piece;

import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.KingDirectionStrategy;

public class King extends Piece {

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
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> findDirection = new KingDirectionStrategy().find(from, to);
		if (findDirection.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Direction direction = findDirection.get();
		if (from.canReach(to, direction.getUnitPosition(), 1)) {
			return direction;
		}
		throw new IllegalArgumentException();
	}
}
