package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Rook extends Piece {

	private final String symbol;

	private Rook(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Rook createWhite(int row, int column) {
		return new Rook(Color.WHITE, new Position(row, column), "♜");
	}

	public static Rook createBlack(int row, int column) {
		return new Rook(Color.BLACK, new Position(row, column) , "♖");
	}

	@Override
	public void move(int row, int column) {
		validatePosition(row, column);
		this.position = this.position.change(row, column);
	}

	private void validatePosition(int row, int column) {
		if (position.isDifferentRow(row) && position.isDifferentColumn(column)) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
