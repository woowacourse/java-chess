package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Status;
import chess.domain.piece.Team;

public class StatusDTO {
	private final String whiteScore;
	private final String blackScore;
	private final String result;

	private StatusDTO(String whiteScore, String blackScore, String result) {
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
		this.result = result;
	}

	public static StatusDTO of(Status status) {
		String whiteScore = String.valueOf(status.toMap().get(Team.WHITE));
		String blackScore = String.valueOf(status.toMap().get(Team.BLACK));
		String result = String.valueOf(status.getWinner().getName());
		return new StatusDTO(whiteScore, blackScore, result);
	}


	public Map<String, String> getStatus() {
		Map<String, String> status = new HashMap<>();
		status.put("whiteScore", whiteScore);
		status.put("blackScore", blackScore);
		status.put("result", result);

		return status;
	}
}
