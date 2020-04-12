package chess.dto;

import java.util.List;

public class MovablePositionsDto {
	private List<String> movablePositionNames;
	private String position;

	public MovablePositionsDto(List<String> movablePositionNames, String position) {
		this.movablePositionNames = movablePositionNames;
		this.position = position;
	}

	public List<String> getMovablePositionNames() {
		return movablePositionNames;
	}

	public String getPosition() {
		return position;
	}
}
