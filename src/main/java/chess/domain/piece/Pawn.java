package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Pawn extends Piece {

	private final String symbol;

	public Pawn(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Pawn createWhite(int row, int column) {
		return new Pawn(Color.WHITE, new Position(row, column), "♜");
	}

	public static Pawn createBlack(int row, int column) {
		return new Pawn(Color.BLACK, new Position(row, column), "♖");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
