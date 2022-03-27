package chess.domain.game;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class ScoreCalculator {

	private final Map<Coordinate, Piece> value;

	public ScoreCalculator(Map<Coordinate, Piece> value) {
		this.value = value;
	}

	public Map<Team, Double> createStatus() {
		return Arrays.stream(Team.values())
			.filter(Team::isNotNone)
			.collect(Collectors.toMap(team -> team, this::calculate));
	}

	public double calculate(Team team) {
		return value.values().stream()
			.filter(piece -> piece.isSameTeam(team))
			.mapToDouble(Piece::getScore)
			.sum() - calculatePawnInSameColumn(calculatePawnCount(team));
	}

	private double calculatePawnInSameColumn(Map<Column, Long> pawnCount) {
		return pawnCount.values()
			.stream()
			.filter(count -> count >= 2)
			.mapToDouble(count -> count * 0.5)
			.sum();
	}

	private Map<Column, Long> calculatePawnCount(Team team) {
		return value.entrySet()
			.stream()
			.filter(piece -> piece.getValue().isSameTeam(team))
			.filter(piece -> piece.getValue().isPawn())
			.collect(Collectors.groupingBy(piece -> piece.getKey().getColumn(), Collectors.counting()));
	}
}
