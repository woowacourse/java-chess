package chess.view;

import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamName {

    private static final Map<String, Team> nameToTeam;

    static {
        nameToTeam = new HashMap<>();
        nameToTeam.put("WHITE", Team.WHITE);
        nameToTeam.put("BLACK", Team.BLACK);
        nameToTeam.put("NONE", Team.NONE);
    }

    public static Team findByName(final String name) {
        return nameToTeam.get(name);
    }
}
