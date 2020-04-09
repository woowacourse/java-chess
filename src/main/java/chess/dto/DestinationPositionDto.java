package chess.dto;

import chess.domain.game.NormalStatus;

public class DestinationPositionDto {
	private String position;
	private NormalStatus normalStatus;

	public DestinationPositionDto(String position, NormalStatus normalStatus) {
		this.position = position;
		this.normalStatus = normalStatus;
	}

	public String getPosition() {
		return position;
	}

	public NormalStatus getNormalStatus() {
		return normalStatus;
	}
}
