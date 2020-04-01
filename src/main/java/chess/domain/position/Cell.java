package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {
	private static final int MINIMUM_DISTANCE = 1;

	private final int number;

	public Cell(int number) {
		this.number = number;
	}

	public static List<Cell> valuesBetween(Cell start, Cell end) {
		List<Cell> result = new ArrayList<>();
		if (start.number > end.number) {
			for (int i = start.number - 1; i > end.number; i--) {
				result.add(new Cell(i));
			}
		} else {
			for (int i = start.number + 1; i < end.number; i++) {
				result.add(new Cell(i));
			}
		}
		return result;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean isNear(Cell other) {
		return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
	}

	public int calculateAbsolute(Cell other) {
		return Math.abs(this.getNumber() - other.getNumber());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cell cell = (Cell)o;
		return number == cell.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return "Cell{" +
			"number=" + number +
			'}';
	}
}
