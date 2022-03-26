package chess.domain.piece;

import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.BishopDirectionStrategy;

public class Bishop extends Piece {

	private final String symbol;

	private Bishop(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Bishop createWhite() {
		return new Bishop(Color.WHITE, "♝");
	}

	public static Bishop createBlack() {
		return new Bishop(Color.BLACK , "♗");
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = new BishopDirectionStrategy().find(from, to);
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
