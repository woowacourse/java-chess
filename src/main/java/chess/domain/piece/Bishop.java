package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.strategy.BishopMovingStrategy;

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
	public boolean isMovable(Position from, Position to) {
		return new BishopMovingStrategy().check(from, to);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
