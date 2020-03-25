package domain.piece.position;

public class Position {
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private Column col;
	private int row;

	public Position(String inputPosition) {
		this.col = Column.of(inputPosition.charAt(COLUMN_INDEX));
		this.row = Character.getNumericValue(inputPosition.charAt(ROW_INDEX));
	}

	public Column getColumn() {
		return col;
	}

	public int getRow() {
		return row;
	}
}
