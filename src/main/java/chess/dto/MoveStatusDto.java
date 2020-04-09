package chess.dto;

import chess.domain.piece.Color;
import chess.web.NormalStatus;

public class MoveStatusDto {
	private NormalStatus normalStatus;
	private String exception;
	private Color winner;

	public MoveStatusDto(NormalStatus normalStatus, String exception, Color winner) {
		this.normalStatus = normalStatus;
		this.exception = exception;
		this.winner = winner;
	}

	public MoveStatusDto(NormalStatus normalStatus) {
		this.normalStatus = normalStatus;
		this.exception = ""; // TODO: 2020/04/09 수정 필요
		this.winner = Color.NONE;
	}

	public MoveStatusDto(NormalStatus normalStatus, Color winner) {
		this.normalStatus = normalStatus;
		this.exception = ""; // TODO: 2020/04/09 수정 필요
		this.winner = winner;
	}

	public NormalStatus getNormalStatus() {
		return normalStatus;
	}

	public String getException() {
		return exception;
	}

	public Color getWinner() {
		return winner;
	}
}
