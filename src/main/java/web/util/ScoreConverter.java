package web.util;

import java.util.Map;
import java.util.stream.Collectors;

import domain.piece.team.Team;

public class ScoreConverter {
	public static Map<String, Double> convert(Map<Team, Double> inputScore) {
		return inputScore.entrySet().stream()
			.collect(Collectors.toMap(k -> k.getKey().getName(), Map.Entry::getValue));
	}
}
