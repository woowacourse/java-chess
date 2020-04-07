package domain.team;

import domain.team.exceptions.CannotFindTeamExcrption;
import java.util.Arrays;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public static Team opposite(Team team) {
        if (BLACK.equals(team)) {
            return WHITE;
        }
        return BLACK;
    }

    public static Team findTeam(String team) {
        return Arrays.stream(Team.values())
            .filter(teams -> teams.toString().equals(team))
            .findFirst()
            .orElseThrow(() -> new CannotFindTeamExcrption("찾을 수 없는 팀입니다."));
    }

    public static Team getInitialTeam(String initial) {
        if (".".equals(initial)) {
            return NONE;
        }
        if (initial.equals(initial.toLowerCase())) {
            return WHITE;
        }
        return BLACK;
    }
}
