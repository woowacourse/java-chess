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
}
