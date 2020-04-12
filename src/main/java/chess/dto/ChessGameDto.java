package chess.dto;

import chess.domain.game.ScoreResult;
import chess.domain.game.Turn;

public class ChessGameDto {
	private BoardDto boardDto;
	private Turn turn;
	private ScoreResult score;
	private boolean normalStatus;

	public ChessGameDto(BoardDto boardDto, Turn turn, ScoreResult score, boolean normalStatus) {
		this.boardDto = boardDto;
		this.turn = turn;
		this.score = score;
		this.normalStatus = normalStatus;
	}

	public BoardDto getBoardDto() {
		return boardDto;
	}

	public Turn getTurn() {
		return turn;
	}

	public ScoreResult getScore() {
		return score;
	}

	public boolean isNormalStatus() {
		return normalStatus;
	}
}
