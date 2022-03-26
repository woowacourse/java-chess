package chess.domain.piece;

import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.RookDirectionStrategy;

public class Rook extends Piece {

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
			throw new IllegalArgumentException();
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
}
