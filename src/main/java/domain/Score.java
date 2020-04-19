package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.team.Team;

public class Score {
	public static Map<Team, Double> calculateScore(List<Piece> pieces, Team... teams) {
		return Arrays.stream(teams)
			.collect(Collectors.toMap(team -> team, team -> calculateScoreByTeam(pieces, team)));
	}

	private static double calculateScoreByTeam(List<Piece> pieces, Team team) {
		double sum = pieces.stream()
			.filter(piece -> piece.isTeam(team))
			.map(Piece::getScore)
			.reduce(0.0, Double::sum);
		return applyPawnScore(pieces, team, sum);
	}

	private static double applyPawnScore(List<Piece> pieces, Team team, double sum) {
		List<Pawn> pawn = pieces.stream()
			.filter(value -> value instanceof Pawn)
			.filter(value -> value.isTeam(team))
			.map(piece -> (Pawn)piece)
			.collect(Collectors.toList());

		if (hasSameColumnPawn(pawn)) {
			sum += pawn.size() * Pawn.PAWN_SCORE_WHEN_HAS_SAME_COLUMN;
		}
		return sum;
	}

	private static boolean hasSameColumnPawn(List<Pawn> pawns) {
		return pawns.stream()
			.anyMatch(pawn -> pawn.hasSameColumn(pawns));
	}
}
