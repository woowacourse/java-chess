package chess.domain.piece;

import java.util.Arrays;

public enum Team {

    WHITE,
    BLACK;

    public static Team of(String value) {
        return Arrays.stream(Team.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    public Team findOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
