package chess.web.dto;

import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessStatusDto {

	private final String turn;
	private final String score;

	private ChessStatusDto(final String turn, final String score) {
		this.turn = turn;
		this.score = score;
	}

	public static ChessStatusDto of(PieceColor pieceColor, Double score) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(score, "점수가 null입니다.");

		return new ChessStatusDto(pieceColor.getColor(), String.valueOf(score));
	}

	public String getTurn() {
		return turn;
	}

	public String getScore() {
		return score;
	}

}
