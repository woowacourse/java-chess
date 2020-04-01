package chess.domain.chessPiece.pieceType;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceColor {

	WHITE("white", String::toLowerCase),
	BLACK("black", String::toUpperCase);

	private final String color;
	private final Function<String, String> convertFrom;

	PieceColor(String color, Function<String, String> convertFrom) {
		this.color = color;
		this.convertFrom = convertFrom;
	}

	public static PieceColor of(String color) {
		return Arrays.stream(values())
			.filter(pieceColor -> pieceColor.color.equals(color))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 색상이 존재하지 않습니다."));
	}

	public String convertFrom(String pieceName) {
		if (pieceName == null || pieceName.isEmpty()) {
			throw new IllegalArgumentException("체스 이름이 존재하지 않습니다.");
		}
		return convertFrom.apply(pieceName);
	}

	public boolean isBlack() {
		return this.equals(BLACK);
	}

	public boolean isWhite() {
		return this.equals(WHITE);
	}

	public String getColor() {
		return color;
	}

}
