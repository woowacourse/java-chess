package chess.domain.piece;

import java.util.Arrays;

public enum Team {

    BLACK("black"),
    WHITE("white"),
    NONE("none");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public static Team of(final String color) {
        return Arrays.stream(values())
                .filter(team -> color.equals(team.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 팀입니다."));
    }

    public Team oppositeTeam() {
        if (this == NONE) {
            throw new IllegalStateException("[ERROR] 상대팀이 없습니다.");
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
