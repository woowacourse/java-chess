package chess.web;

import java.util.Arrays;

public enum WebChessPiece {

	BLACK_PAWN("P", "<img class=\"chessboard\" src=\"./images/black-pawn.png\">"),
	WHITE_PAWN("p", "<img class=\"chessboard\" src=\"./images/white-pawn.png\">"),
	BLACK_KNIGHT("N", "<img class=\"chessboard\" src=\"./images/black-knight.png\">"),
	WHITE_KNIGHT("n", "<img class=\"chessboard\" src=\"./images/white-knight.png\">"),
	BLACK_BISHOP("B", "<img class=\"chessboard\" src=\"./images/black-bishop.png\">"),
	WHITE_BISHOP("b", "<img class=\"chessboard\" src=\"./images/white-bishop.png\">"),
	BLACK_ROOK("R", "<img class=\"chessboard\" src=\"./images/black-rook.png\">"),
	WHITE_ROOK("r", "<img class=\"chessboard\" src=\"./images/white-rook.png\">"),
	BLACK_QUEEN("Q", "<img class=\"chessboard\" src=\"./images/black-queen.png\">"),
	WHITE_QUEEN("q", "<img class=\"chessboard\" src=\"./images/white-queen.png\">"),
	BLACK_KING("K", "<img class=\"chessboard\" src=\"./images/black-king.png\">"),
	WHITE_KING("k", "<img class=\"chessboard\" src=\"./images/white-king.png\">");

	private final String chessPieceName;
	private final String imageUrl;

	WebChessPiece(final String chessPieceName, final String imageUrl) {
		this.chessPieceName = chessPieceName;
		this.imageUrl = imageUrl;
	}

	public static WebChessPiece of(String chessPieceName) {
		return Arrays.stream(values())
			.filter(value -> value.chessPieceName.equals(chessPieceName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 피스가 유효하지 않습니다."));
	}

	public String getImageUrl() {
		return imageUrl;
	}

}
