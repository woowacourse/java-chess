package chess.dto;

import chess.domain.piece.Color;

public class MoveStatusDto {
	private boolean normalStatus;
	private Color winner;

	public MoveStatusDto(boolean normalStatus, Color winner) {
		this.normalStatus = normalStatus;
		this.winner = winner;
	}

	public MoveStatusDto(boolean normalStatus) {
		this.normalStatus = normalStatus;
		this.winner = Color.NONE;
	}

	public boolean getNormalStatus() {
		return normalStatus;
	}

	public Color getWinner() {
		return winner;
	}
}
