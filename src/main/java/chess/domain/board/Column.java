package chess.domain.board;

import java.util.Arrays;

/**
 *    체스판 열을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Column {
	A("a", 8),
	B("b", 7),
	C("c", 6),
	D("d", 5),
	E("e", 4),
	F("f", 3),
	G("g", 2),
	H("h", 1);

	private String column;
	private int number;

	Column(String column, int number) {
		this.column = column;
		this.number = number;
	}

	public static Column of(int column) {
		return Arrays.stream(chess.domain.board.Column.values())
			.filter(file -> column == file.number)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못입력하셨습니다!"));
	}

	public String getColumn() {
		return column;
	}

	public int getNumber() {
		return number;
	}
}
