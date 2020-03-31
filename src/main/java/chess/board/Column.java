package chess.board;

import java.util.Arrays;

public enum Column {
	A('a'),
	B('b'),
	C('c'),
	D('d'),
	E('e'),
	F('f'),
	G('g'),
	H('h');

	private final char symbol;

	Column(char symbol) {
		this.symbol = symbol;
	}

	public static Column of(char symbol) {
		return Arrays.stream(values())
			.filter(column -> column.symbol == symbol)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("찾으려는 문자가 없습니다."));
	}

	public int minus(Column column) {
		return this.symbol - column.symbol;
	}

	public boolean isGreaterThan(Column other) {
		return this.symbol > other.symbol;
	}

	public char getSymbol() {
		return symbol;
	}

	public Column add(int colWeight) {
		return of((char)(this.symbol + colWeight));
	}
}
