package chess.domain.board;

import java.util.Arrays;

/**
 *    체스판 열을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum File {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private String file;
	private int column;

	File(String file, int column) {
		this.file = file;
		this.column = column;
	}

	public static File of(int column) {
		return Arrays.stream(File.values())
			.filter(file -> file.getColumn() == column)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 file값 입니다."));
	}

	public String getFile() {
		return file;
	}

	public int getColumn() {
		return column;
	}
}
