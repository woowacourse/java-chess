package domain.piece.position;

public enum Column {
	A('a', 1),
	B('b', 2),
	C('c', 3),
	D('d', 4),
	E('e', 5),
	F('f', 6),
	G('g', 7),
	H('h', 8);

	private char columnName;
	private int number;

	Column(char columnName, int number) {
		this.columnName = columnName;
		this.number = number;
	}

	public char getColumnName() {
		return columnName;
	}

	public int getNumber() {
		return number;
	}
}
