package chess.dto;

import chess.domain.piece.Team;
import java.util.Arrays;

public enum TeamName {
    WHITE_TEAM_NAME(Team.WHITE, "백팀"),
    BLACK_TEAM_NAME(Team.BLACK, "흑팀");

    private final Team team;
    private final String teamName;

    TeamName(final Team team, final String teamName) {
        this.team = team;

        this.teamName = teamName;
    }

    static String getTeamName(final Team team) {
        return Arrays.stream(TeamName.values())
                .filter(it -> it.team == team)
                .map(it -> it.teamName)
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 팀이 없습니다.");
                });
    }
}
