package chess.dto;

import chess.web.NormalStatus;

public class MovableStatusDto {
	private NormalStatus normalStatus;
	private String position;

	public MovableStatusDto(NormalStatus normalStatus, String position) {
		this.normalStatus = normalStatus;
		this.position = position;
	}

	public NormalStatus getNormalStatus() {
		return normalStatus;
	}

	public String getPosition() {
		return position;
	}
}
