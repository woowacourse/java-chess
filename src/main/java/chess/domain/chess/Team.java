package chess.domain.chess;

import java.util.function.Function;

public enum Team {
    WHITE(1, String::toLowerCase),
    BLACK(2, String::toUpperCase);

    private int teamId;
    private Function<String, String> function;

    Team(int teamId, Function<String, String> function) {
        this.teamId = teamId;
        this.function = function;
    }

    public String getUnitName(String name) {
        return this.function.apply(name);
    }

    public int getTeamId() {
        return teamId;
    }
}
