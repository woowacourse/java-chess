package chess.domain.piece;

import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.QueenDirectionStrategy;

public class Queen extends Piece {

	private final String symbol;

	private Queen(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Queen createWhite() {
		return new Queen(Color.WHITE, "♛");
	}

	public static Queen createBlack() {
		return new Queen(Color.BLACK, "♕");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = new QueenDirectionStrategy().find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return direction.get();
	}

	@Override
	public boolean isPawn() {
		return false;
	}
}
