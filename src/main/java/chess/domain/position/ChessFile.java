package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

public enum ChessFile {

	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private final String chessFile;
	private final int fileValue;

	ChessFile(String chessFile, int fileValue) {
		this.chessFile = chessFile;
		this.fileValue = fileValue;
	}

	public static ChessFile from(char chessFile) {
		return Arrays.stream(values())
			.filter(value -> value.chessFile.equals(String.valueOf(chessFile)))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 파일이 존재하지 않습니다."));
	}

	public static ChessFile from(String chessFile) {
		return Arrays.stream(values())
			.filter(value -> value.chessFile.equals(chessFile))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 파일이 존재하지 않습니다."));
	}

	public static ChessFile from(int fileValue) {
		return Arrays.stream(values())
			.filter(value -> value.fileValue == fileValue)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 파일이 존재하지 않습니다."));
	}

	ChessFile move(int movingFileValue) {
		return from(this.fileValue + movingFileValue);
	}

	public int gapTo(ChessFile targetChessFile) {
		Objects.requireNonNull(targetChessFile, "체스 파일이 null입니다.");
		return targetChessFile.fileValue - this.fileValue;
	}

	@Override
	public String toString() {
		return String.valueOf(chessFile);
	}

}
