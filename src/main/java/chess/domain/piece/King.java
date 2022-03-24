package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.strategy.KingMovingStrategy;

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
	public boolean isMovable(Position from, Position to) {
		return new KingMovingStrategy().check(from, to);
	}
}
