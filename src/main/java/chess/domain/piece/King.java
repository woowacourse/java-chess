package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class King extends Piece {

	private final String symbol;

	private King(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static King createWhite(int row, int column) {
		return new King(Color.WHITE, new Position(row, column) , "♚");
	}

	public static King createBlack(int row, int column) {
		return new King(Color.BLACK, new Position(row, column) , "♔");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
