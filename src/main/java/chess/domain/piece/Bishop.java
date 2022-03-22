package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Bishop extends Piece {

	private final String symbol;

	private Bishop(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Bishop createWhite(int row, int column) {
		return new Bishop(Color.WHITE, new Position(row, column), "♝");
	}

	public static Bishop createBlack(int row, int column) {
		return new Bishop(Color.BLACK, new Position(row, column) , "♗");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
