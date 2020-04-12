package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public enum Team {
    WHITE("white"),
    BLACK("black"),
    BLANK("blank");

    private static final Map<String, Team> ENUM_MAP = new HashMap<>();

    static {
        for (Team team : values()) {
            ENUM_MAP.put(team.name, team);
        }
    }

    private String name;

    Team(String name) {
        this.name = name;
    }
    public static Team of(final String name) {
        return ENUM_MAP.get(name);
    }


    public boolean isNotSame(final Team team) {
        return this != team && this != BLANK && team != BLANK;
    }
}
