package chess.domain;

import java.util.Collections;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.PieceScore;
import chess.domain.piece.Team;

public class Status {
	private final Map<Team, Double> status;

	private Status(Map<Team, Double> status) {
		this.status = status;
	}

	public static Status of(Board board) {
		return new Status(Map.of(
			Team.BLACK, calculateOf(board, Team.BLACK),
			Team.WHITE, calculateOf(board, Team.WHITE))
		);
	}

	private static double calculateOf(Board board, Team team) {
		return board.getColumnGroupOf(team)
			.stream()
			.mapToDouble(PieceScore::calculateScoreOf)
			.sum();
	}

	public Team getWinner() {
		if (status.get(Team.BLACK).equals(status.get(Team.WHITE))) {
			return Team.NONE;
		}

		double winnerScore = Collections.max(status.values());
		return status.keySet()
			.stream()
			.filter(key -> status.get(key).equals(winnerScore))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public Map<Team, Double> toMap() {
		return Collections.unmodifiableMap(status);
	}
}