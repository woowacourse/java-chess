package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.strategy.BlackPawnMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.WhitePawnMovingStrategy;

public class Pawn extends Piece {

	private final String symbol;

	public Pawn(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Pawn createWhite() {
		return new Pawn(Color.WHITE, "♟");
	}

	public static Pawn createBlack() {
		return new Pawn(Color.BLACK, "♙");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public boolean isMovable(Position from, Position to) {
		return findStrategy().check(from, to);
	}

	private MovingStrategy findStrategy() {
		if (this.color == Color.WHITE) {
			return new WhitePawnMovingStrategy();
		}
		return new BlackPawnMovingStrategy();
	}
}
