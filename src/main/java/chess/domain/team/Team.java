package chess.domain.team;

import java.util.Map;

public enum Team {

    BLACK,
    WHITE,
    NONE;

    private static final Map<Team, Team> opposite = Map.of(
            BLACK, WHITE,
            WHITE, BLACK,
            NONE, NONE
    );

    public Team reverse() {
        return opposite.get(this);
    }
}
