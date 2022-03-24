package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Knight extends Piece {

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
	public boolean isMovable(Position from, Position to) {
		return new KnightMovingStrategy().check(from, to);
	}
}
