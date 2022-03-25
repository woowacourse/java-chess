package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.strategy.QueenMovingStrategy;

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
	public boolean isMovable(Position from, Position to) {
		return new QueenMovingStrategy().check(from, to);
	}

	@Override
	public boolean isPawn() {
		return false;
	}
}
