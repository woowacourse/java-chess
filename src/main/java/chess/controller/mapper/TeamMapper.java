package chess.controller.mapper;

import java.util.Map;
import java.util.Objects;

import chess.domain.game.Team;

public class TeamMapper {

    private static final Map<Team, String> TEAM_MAP = Map.ofEntries(
            Map.entry(Team.BLACK, "흑"),
            Map.entry(Team.WHITE, "백"),
            Map.entry(Team.NEUTRAL, "무")
    );

    public static String map(Team team) {
        String predefinedName = TEAM_MAP.get(team);
        if (Objects.isNull(predefinedName)) {
            throw new IllegalArgumentException("알 수 없는 팀이 있습니다");
        }
        return predefinedName;
    }
}
