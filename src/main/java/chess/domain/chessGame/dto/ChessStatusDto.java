package chess.domain.chessGame.dto;

import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessStatusDto {

	private final String color;
	private final String score;

	private ChessStatusDto(final String color, final String score) {
		this.color = color;
		this.score = score;
	}

	public static ChessStatusDto of(PieceColor pieceColor, Double score) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(score, "점수가 null입니다.");

		return new ChessStatusDto(pieceColor.getColor(), String.valueOf(score));
	}

	public String getScore() {
		return score;
	}

}
