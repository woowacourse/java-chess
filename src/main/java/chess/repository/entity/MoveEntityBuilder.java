package chess.repository.entity;

public class MoveEntityBuilder {

	private long id;
	private long gameId;
	private int sourceColumn;
	private int sourceRow;
	private int targetColumn;
	private int targetRow;

	public MoveEntityBuilder id(final long id) {
		this.id = id;
		return this;
	}

	public MoveEntityBuilder gameId(final long gameId) {
		this.gameId = gameId;
		return this;
	}

	public MoveEntityBuilder sourceColumn(final int sourceColumn) {
		this.sourceColumn = sourceColumn;
		return this;
	}

	public MoveEntityBuilder sourceRow(final int sourceRow) {
		this.sourceRow = sourceRow;
		return this;
	}

	public MoveEntityBuilder targetColumn(final int targetColumn) {
		this.targetColumn = targetColumn;
		return this;
	}

	public MoveEntityBuilder targetRow(final int targetRow) {
		this.targetRow = targetRow;
		return this;
	}

	public MoveEntity build() {
		return new MoveEntity(id, gameId, sourceColumn, sourceRow, targetColumn, targetRow);
	}
}
