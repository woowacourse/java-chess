package chess.domain.dto;

import chess.domain.ChessGame;
import chess.domain.Side;

public class StatusDto {
	private double whiteScore;
	private double blackScore;

	private StatusDto(double whiteScore, double blackScore) {
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
	}

	public static StatusDto of(ChessGame chessGame) {
		return new StatusDto(chessGame.status(Side.WHITE), chessGame.status(Side.BLACK));
	}

	public double getWhiteScore() {
		return whiteScore;
	}

	public double getBlackScore() {
		return blackScore;
	}
}
