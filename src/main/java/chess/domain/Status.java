package chess.domain;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.piece.Team;

public class Status {
	private final Map<Team, Double> status;

	private Status(Map<Team, Double> status) {
		this.status = status;
	}

	public static Status of(List<Piece> pieces) {
		return new Status(Map.of(
			Team.BLACK, calculateOf(groupByTeam(pieces, Team.BLACK)),
			Team.WHITE, calculateOf(groupByTeam(pieces, Team.WHITE))
		));
	}

	private static List<Piece> groupByTeam(List<Piece> pieces, Team team) {
		return pieces.stream()
			.filter(piece -> piece.isSameTeam(team))
			.collect(Collectors.toList());
	}

	private static double calculateOf(List<Piece> teams) {
		return groupByColumn(teams)
			.stream()
			.mapToDouble(PieceScore::calculateScoreOf)
			.sum();
	}

	private static Collection<List<Piece>> groupByColumn(List<Piece> teams) {
		return teams.stream()
			.collect(groupingBy(
				piece -> piece.getPosition().getColumn(),
				mapping(Function.identity(), toList())))
			.values();
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
