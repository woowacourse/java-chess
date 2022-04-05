package chess.domain.piece;

import java.util.Arrays;

public enum Team {

    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public static Team of(final String value) {
        return Arrays.stream(values())
                .filter(team -> team.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Team 입니다."));
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
