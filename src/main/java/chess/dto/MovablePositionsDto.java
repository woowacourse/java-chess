package chess.dto;

import chess.web.NormalStatus;

import java.util.Collections;
import java.util.List;

public class MovablePositionsDto {
	private List<String> movablePositionNames;
	private String position;
	private NormalStatus normalStatus;
	private String exception;

	public MovablePositionsDto(List<String> movablePositionNames, String position, NormalStatus normalStatus) {
		this.movablePositionNames = movablePositionNames;
		this.position = position;
		this.normalStatus = normalStatus;
		this.exception = "";
	}

	public MovablePositionsDto(String exception) {
		this.movablePositionNames = Collections.emptyList();
		position = "";
		normalStatus = NormalStatus.NO;
		this.exception = exception;

	}

	public List<String> getMovablePositionNames() {
		return movablePositionNames;
	}

	public String getPosition() {
		return position;
	}

	public NormalStatus getNormalStatus() {
		return normalStatus;
	}

	public String getException() {
		return exception;
	}
}
