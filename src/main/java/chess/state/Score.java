package chess.state;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class Score {
	private static final Map<Team, Double> scores = new HashMap<>();

	public static Map<Team, Double> of(Board board) {
		scores.put(Team.WHITE, getSum(board, Team.WHITE));
		scores.put(Team.BLACK, getSum(board, Team.BLACK));
		return scores;
	}

	private static double getSum(Board board, Team team) {
		return board.getBoard().values().stream()
			.filter(value -> value.getTeam() == team)
			.mapToDouble(Piece::getScore)
			.sum();
	}
}
