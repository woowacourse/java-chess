package chess.dto;

public class MoveDto {

	private final int sourceColumn;
	private final int sourceRow;
	private final int targetColumn;
	private final int targetRow;

	public MoveDto(final int sourceColumn, final int sourceRow, final int targetColumn, final int targetRow) {
		this.sourceColumn = sourceColumn;
		this.sourceRow = sourceRow;
		this.targetColumn = targetColumn;
		this.targetRow = targetRow;
	}

	public int getSourceColumn() {
		return sourceColumn;
	}

	public int getSourceRow() {
		return sourceRow;
	}

	public int getTargetColumn() {
		return targetColumn;
	}

	public int getTargetRow() {
		return targetRow;
	}
}
