package chess.dto;

import chess.domain.Team;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameResultDto {
    private final Map<String, Double> scores;
    private final List<String> winningTeams;

    private GameResultDto(final Map<String, Double> scores, final List<String> winningTeams) {
        this.scores = scores;
        this.winningTeams = winningTeams;
    }

    public static GameResultDto from(final Map<Team, Double> totalScore, final List<Team> teams) {
        final Map<String, Double> scores = totalScore.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
        return new GameResultDto(scores, teams.stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    public List<String> getWinningTeams() {
        return Collections.unmodifiableList(winningTeams);
    }

    public Map<String, Double> getScores() {
        return Collections.unmodifiableMap(scores);
    }
}
