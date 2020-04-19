package chess.service.dto;

import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;

public class PieceColorDto {

	private final String pieceColor;

	private PieceColorDto(final String pieceColor) {
		this.pieceColor = pieceColor;
	}

	public static PieceColorDto of(final PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");

		return new PieceColorDto(pieceColor.getColor());
	}

	public String getPieceColor() {
		return pieceColor;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final PieceColorDto that = (PieceColorDto)object;
		return Objects.equals(pieceColor, that.pieceColor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieceColor);
	}

}
