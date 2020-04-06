package chess.web.dto;

import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;

public class PieceColorDto {

	private final String color;

	private PieceColorDto(final String color) {
		this.color = color;
	}

	public static PieceColorDto of(final PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");

		return new PieceColorDto(pieceColor.getColor());
	}

	public String getColor() {
		return color;
	}

}
