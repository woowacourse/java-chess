package domain.piece.position;

import static domain.piece.position.InvalidPositionException.*;

import java.util.Arrays;

public enum Column {
	A('a', 1),
	B('b', 2),
	C('c', 3),
	D('d', 4),
	E('e', 5),
	F('f', 6),
	G('g', 7),
	H('h', 8);

	private char representation;
	private int number;

	Column(char representation, int number) {
		this.representation = representation;
		this.number = number;
	}

	public static Column of(char representation) {
		return Arrays.stream(Column.values())
			.filter(column -> representation == column.getRepresentation())
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(INVALID_COLUMN));
	}

	public char getRepresentation() {
		return representation;
	}

	public int getNumber() {
		return number;
	}
}
