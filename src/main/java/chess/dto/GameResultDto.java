package chess.dto;

import chess.domain.Team;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {
    private final List<String> winningTeams;

    private GameResultDto(final List<String> winningTeams) {
        this.winningTeams = winningTeams;
    }

    public static GameResultDto from(List<Team> teams) {
        return new GameResultDto(teams.stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    public List<String> getWinningTeams() {
        return Collections.unmodifiableList(winningTeams);
    }
}
