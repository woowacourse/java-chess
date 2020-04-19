package chess.service.dto;

import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessStatusDto {

	private final String pieceColor;
	private final String score;

	private ChessStatusDto(final String pieceColor, final String score) {
		this.pieceColor = pieceColor;
		this.score = score;
	}

	public static ChessStatusDto of(PieceColor pieceColor, Double score) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(score, "점수가 null입니다.");

		return new ChessStatusDto(pieceColor.getColor(), String.valueOf(score));
	}

	public String getPieceColor() {
		return pieceColor;
	}

	public String getScore() {
		return score;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessStatusDto that = (ChessStatusDto)object;
		return Objects.equals(pieceColor, that.pieceColor) &&
			Objects.equals(score, that.score);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieceColor, score);
	}

}
