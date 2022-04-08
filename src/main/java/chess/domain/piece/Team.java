package chess.domain.piece;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Team {

    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    private static Map<String, Team> CACHE =
            Arrays.stream(values()).collect(Collectors.toMap(Team::getValue, Function.identity()));

    public static Team of(final String value) {
        return Optional
                .ofNullable(CACHE.get(value))
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Team 입니다."));
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
