package chess.domain.chess;

import java.util.function.Function;

public enum Team {
    WHITE(1, String::toLowerCase, "WHITE"),
    BLACK(2, String::toUpperCase, "BLACK");

    private int teamId;
    private Function<String, String> function;
    private String name;

    Team(int teamId, Function<String, String> function, String name) {
        this.teamId = teamId;
        this.function = function;
        this.name = name;
    }

    public static Team getTeamById(int teamId) {
        if (teamId == Team.BLACK.teamId) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public String getUnitName(String name) {
        return this.function.apply(name);
    }

    public int getTeamId() {
        return teamId;
    }

}
