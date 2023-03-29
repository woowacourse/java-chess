package chess.view;

import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.team.Team.BLACK;
import static chess.domain.team.Team.NONE;
import static chess.domain.team.Team.WHITE;

public class TeamName {

    private static final Map<String, Team> nameToTeam;
    private static final Map<Team, String> teamToName;

    static {
        nameToTeam = new HashMap<>();
        nameToTeam.put("WHITE", WHITE);
        nameToTeam.put("BLACK", BLACK);
        nameToTeam.put("NONE", NONE);

        teamToName = new HashMap<>();
        teamToName.put(WHITE, "WHITE");
        teamToName.put(BLACK, "BLACK");
        teamToName.put(NONE, "NONE");
    }

    public static Team findByName(final String name) {
        return nameToTeam.get(name);
    }

    public static String findByTeam(final Team team) {
        return teamToName.get(team);
    }
}
