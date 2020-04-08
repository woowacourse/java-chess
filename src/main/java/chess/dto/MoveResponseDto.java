package chess.dto;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.GameResult;

public class MoveResponseDto {
	private boolean isSuccessToMove;
	private boolean isEndOfGame;
	private double whiteScore;
	private double blackScore;
	private String message;

	public MoveResponseDto(boolean isSuccessToMove, boolean isEndOfGame, double whiteScore, double blackScore,
			String message) {
		this.isSuccessToMove = isSuccessToMove;
		this.isEndOfGame = isEndOfGame;
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
		this.message = message;
	}

	public static MoveResponseDto ofSuccessToMove(boolean isSuccessToMove, boolean isEndOfGame, GameResult gameResult) {
		return new MoveResponseDto(isSuccessToMove, isEndOfGame, gameResult.getScoreBy(WHITE),
				gameResult.getScoreBy(BLACK), "성공적으로 이동했습니다.");
	}

	public static MoveResponseDto ofFailedToMove(String message) {
		return new MoveResponseDto(false, false, 0, 0, message);
	}

	public boolean isSuccessToMove() {
		return isSuccessToMove;
	}

	public void setSuccessToMove(boolean successToMove) {
		isSuccessToMove = successToMove;
	}

	public boolean isEndOfGame() {
		return isEndOfGame;
	}

	public void setEndOfGame(boolean endOfGame) {
		isEndOfGame = endOfGame;
	}

	public double getWhiteScore() {
		return whiteScore;
	}

	public void setWhiteScore(double whiteScore) {
		this.whiteScore = whiteScore;
	}

	public double getBlackScore() {
		return blackScore;
	}

	public void setBlackScore(double blackScore) {
		this.blackScore = blackScore;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
