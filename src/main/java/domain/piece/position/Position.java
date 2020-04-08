package domain.piece.position;

import java.util.Arrays;

import domain.piece.team.Team;

public class Position {
	private Column column;
	private Row row;

	Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(String position) {
		return PositionCache.of(position);
	}

	public static Position of(Column column, Row row) {
		return of(column.getColumnName() + row.getNumber());
	}

	public static Position of(int inputColumn, int inputRow) {
		Column column = Arrays.stream(Column.values())
			.filter(value -> value.getNumber() == inputColumn)
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_POSITION));

		Row row = Arrays.stream(Row.values())
			.filter(value -> value.getNumber() == inputRow)
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_POSITION));

		return of(column, row);
	}

	public int calculateColumnGap(Position targetPosition) {
		return column.gap(targetPosition.column);
	}

	public int calculateRowGap(Position targetPosition) {
		return row.gap(targetPosition.row);
	}

	public boolean isSamePosition(Position position) {
		return this.equals(position);
	}

	public boolean isSameColumn(Position position) {
		return this.column.equals(position.column);
	}

	public boolean isStartRow(Team team) {
		return this.row.isStartRow(team.getStartRankIndex());
	}

	public Column getColumn() {
		return column;
	}

	public int getColumnNumber() {
		return column.getNumber();
	}

	public int getRowNumber() {
		return row.getNumber();
	}

	public int getRankIndex() {
		return row.getRankIndex();
	}
}