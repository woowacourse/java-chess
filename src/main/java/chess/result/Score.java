package chess.result;

import java.util.Map;

import chess.piece.Piece;
import chess.team.Team;

public class Score {
	private final Team team;
	private final double amount;

	public Score(Team team, Map<Piece, Boolean> pieces) {
		this.amount = calculateScore(pieces);
		this.team = team;
	}

	public double calculateScore(Map<Piece, Boolean> pieces) {
		return pieces.keySet().stream()
			.mapToDouble(piece -> piece.getScore(pieces.get(piece)))
			.sum();
	}

	public Team getTeam() {
		return team;
	}

	public double getAmount() {
		return amount;
	}

	public int compare(Score other) {
		return Double.compare(this.amount, other.amount);
	}
}
