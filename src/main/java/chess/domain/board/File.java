package chess.domain.board;

import java.util.Arrays;

/**
 *    체스판 열을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum File {
	A("a", 8),
	B("b", 7),
	C("c", 6),
	D("d", 5),
	E("e", 4),
	F("f", 3),
	G("g", 2),
	H("h", 1);

	private String file;
	private int column;

	File(String file, int column) {
		this.file = file;
		this.column = column;
	}

	public static File of(int column) {
		return Arrays.stream(File.values())
			.filter(file -> column == file.column)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못입력하셨습니다!"));
	}

	public String getFile() {
		return file;
	}

	public int getColumn() {
		return column;
	}
}
