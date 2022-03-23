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

	public void move(int row, int column) {
		validatePosition(row, column);
		this.position = this.position.change(row, column);
	}

	private void validatePosition(int row, int column) {
		if (this.position.isNotAbsoluteSlopeOne(row, column)) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}
}
