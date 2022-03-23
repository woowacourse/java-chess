package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class Queen extends Piece {

	private final String symbol;

	private Queen(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Queen createWhite(int row, int column) {
		return new Queen(Color.WHITE, new Position(row, column) , "♛");
	}

	public static Queen createBlack(int row, int column) {
		return new Queen(Color.BLACK, new Position(row, column) , "♕");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	public void move(int row, int column) {
		validatePosition(row, column);
		this.position = this.position.change(row, column);
	}

	private void validatePosition(int row, int column) {
		if ((position.isDifferentRow(row) && position.isDifferentColumn(column)) &&
			this.position.isNotAbsoluteSlopeOne(row, column)) {
			throw new IllegalArgumentException();
		}
	}
}
