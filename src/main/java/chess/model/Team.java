package chess.model;

import java.util.Arrays;

public enum Team {

    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE"),
    ;

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 값이 없습니다."));
    }

    public String getOpponentTeam() {
        if (this == BLACK) {
            return WHITE.name;
        }
        if (this == WHITE) {
            return BLACK.name;
        }
        throw new IllegalArgumentException("[ERROR] 올바른 값이 아닙니다.");
    }

    public String getName() {
        return name;
    }
}
