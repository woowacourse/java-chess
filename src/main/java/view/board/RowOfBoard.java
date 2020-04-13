package view.board;

import domain.coordinate.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RowOfBoard {
	private static final String EMPTY = " ";

	private final List<String> rowOfBoard;

	private RowOfBoard(List<String> rowOfBoard) {
		this.rowOfBoard = new ArrayList<>(rowOfBoard);
	}

	public static RowOfBoard createEmpty() {
		List<String> rowOfBoard = new ArrayList<>();
		for (Column column : Column.values()) {
			rowOfBoard.add(EMPTY);
		}
		return new RowOfBoard(rowOfBoard);
	}

	public void putOnColumn(int column, String initial) {
		rowOfBoard.set(column, initial);
	}

	public List<String> getRowOfBoard() {
		return Collections.unmodifiableList(rowOfBoard);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RowOfBoard that = (RowOfBoard) o;
		return Objects.equals(rowOfBoard, that.rowOfBoard);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rowOfBoard);
	}

	@Override
	public String toString() {
		return "RowOfBoard{" +
				"rowOfBoard=" + rowOfBoard +
				'}';
	}
}
