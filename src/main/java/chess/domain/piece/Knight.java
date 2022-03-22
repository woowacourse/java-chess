package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Knight extends Piece {

	private final String symbol;

	private Knight(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Knight createWhite(int row, int column) {
		return new Knight(Color.WHITE, new Position(row, column), "♞");
	}

	public static Knight createBlack(int row, int column) {
		return new Knight(Color.BLACK, new Position(row, column) , "♘");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
