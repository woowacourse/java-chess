package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.board.Rank;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.team.Team;

public class Score {
	public static Map<Team, Double> calculateScore(List<Rank> ranks, Team... teams) {
		return Arrays.stream(teams)
			.collect(Collectors.toMap(team -> team, team -> calculateScoreByTeam(ranks, team)));
	}

	private static double calculateScoreByTeam(List<Rank> ranks, Team team) {
		double sum = ranks.stream()
			.map(rank -> rank.calculateScore(team))
			.reduce(0.0, Double::sum);

		return applyPawnScore(ranks, team, sum);
	}

	private static double applyPawnScore(List<Rank> ranks, Team team, double sum) {
		List<Piece> pawn = new ArrayList<>();

		ranks.stream()
			.map(rank -> rank.findPawn(team))
			.map(pawn::addAll)
			.close();

		if (hasSameColumnPawn(pawn)) {
			sum += pawn.size() * Pawn.PAWN_SCORE_WHEN_HAS_SAME_COLUMN;
		}
		return sum;
	}

	private static boolean hasSameColumnPawn(List<Piece> pawns) {
		int distinctCount = (int)pawns.stream()
			.map(pawn -> pawn.getPosition().getColumn())
			.distinct()
			.count();

		return pawns.size() != distinctCount;
	}
}
