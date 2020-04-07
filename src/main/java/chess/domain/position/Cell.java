package chess.domain.position;

import static chess.domain.piece.Team.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.Team;

public class Cell {
	private static final Map<Team, Cell> PAWN_INITIAL_ROW;
	private static final int MINIMUM_DISTANCE = 1;

	static {
		PAWN_INITIAL_ROW = new HashMap<>();
		PAWN_INITIAL_ROW.put(BLACK, new Cell(7));
		PAWN_INITIAL_ROW.put(WHITE, new Cell(2));
	}

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

	public boolean isInitialPawnRow(Team team) {
		return this.equals(PAWN_INITIAL_ROW.get(team));
	}
}
