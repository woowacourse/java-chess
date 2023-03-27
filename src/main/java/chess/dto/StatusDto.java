package chess.dto;

import chess.domain.piece.Score;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class StatusDto {

    private final Map<String, Double> statusDto = new HashMap<>();

    public StatusDto(final Map<Team, Score> status) {
        status.forEach((key, value) -> {
            final String teamName = TeamName.getTeamName(key);
            final Double score = value.getScore();
            statusDto.put(teamName, score);
        });
    }

    public Map<String, Double> getStatus() {
        return this.statusDto;
    }
}
