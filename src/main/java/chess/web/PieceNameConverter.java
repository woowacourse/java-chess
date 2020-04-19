package chess.web;

import java.util.Arrays;

// NOTE: 2020/04/16 web, console용 컨버터 생성해보기.
public enum PieceNameConverter {

	BLACK_KING("K", "black-king"),
	WHITE_KING("k", "white-king"),
	BLACK_KNIGHT("N", "black-knight"),
	WHITE_KNIGHT("n", "white-knight"),
	BLACK_QUEEN("Q", "black-queen"),
	WHITE_QUEEN("q", "white-queen"),
	BLACK_ROOK("R", "black-rook"),
	WHITE_ROOK("r", "white-rook"),
	BLACK_BISHOP("B", "black-bishop"),
	WHITE_BISHOP("b", "white-bishop"),
	BLACK_PAWN("P", "black-pawn"),
	WHITE_PAWN("p", "white-pawn");

	private static final String IMAGE_SOURCE_FORMAT = "<img class=\"chessboard\" src=\"./images/%s.png\">";

	private final String chessPieceName;
	private final String imageFileName;

	PieceNameConverter(final String chessPieceName, final String imageFileName) {
		this.chessPieceName = chessPieceName;
		this.imageFileName = imageFileName;
	}

	public static PieceNameConverter of(String chessPieceName) {
		return Arrays.stream(values())
			.filter(value -> value.chessPieceName.equals(chessPieceName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 피스가 유효하지 않습니다."));
	}

	public String getImageFileName() {
		return String.format(IMAGE_SOURCE_FORMAT, imageFileName);
	}

}
