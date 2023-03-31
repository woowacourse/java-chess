package chess.repository.entity;

public class MoveEntity implements Entity {

	private final long id;
	private final long game_id;
	private final int sourceColumn;
	private final int sourceRow;
	private final int targetColumn;
	private final int targetRow;

	public MoveEntity(final long id, final long game_id, final int sourceColumn, final int sourceRow,
		final int targetColumn, final int targetRow) {
		this.id = id;
		this.game_id = game_id;
		this.sourceColumn = sourceColumn;
		this.sourceRow = sourceRow;
		this.targetColumn = targetColumn;
		this.targetRow = targetRow;
	}

	public long getId() {
		return id;
	}

	public long getGame_id() {
		return game_id;
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
