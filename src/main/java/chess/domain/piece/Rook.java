package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

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
	public boolean isMovable(Position from, Position to) {
		return new RookMovingStrategy().check(from, to);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
