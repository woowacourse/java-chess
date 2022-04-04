package chess.webview;

import chess.domain.Team;
import java.util.Arrays;

public enum TeamName {
    BLACK(Team.BLACK, "black"),
    WHITE(Team.WHITE, "white");

    private static final String UNVALID_TEAM_EXCEPTION = "[ERROR] 유효하지 않은 팀입니다.";
    private final Team team;
    private final String name;

    TeamName(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public static String of(Team team) {
        return Arrays.stream(TeamName.values())
                .filter(it -> it.team.equals(team))
                .map(it -> it.name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_TEAM_EXCEPTION));
    }
}
