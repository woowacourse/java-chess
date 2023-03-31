package chess.domain.piece;

import chess.domain.board.RankCoordinate;

import java.util.Arrays;
import java.util.List;

import static chess.domain.board.RankCoordinate.*;

public enum Team {
    BLACK(List.of(SEVEN, EIGHT), -1),
    WHITE(List.of(ONE, TWO), 1),
    EMPTY(List.of(THREE, FOUR, FIVE, SIX), 0);

    private final List<RankCoordinate> rankCoordinates;
    private final int direction;

    Team(List<RankCoordinate> rankCoordinates, int direction) {
        this.rankCoordinates = rankCoordinates;
        this.direction = direction;
    }

    public static Team from(RankCoordinate rankCoordinate) {
        return Arrays.stream(values())
                .filter(team -> team.rankCoordinates.contains(rankCoordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_COORDINATE_MESSAGE));
    }

    public boolean isOpposite(Team team) {
        return this.getReverseTeam() == team;
    }

    public Team getReverseTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }

    public int getDirection() {
        return this.direction;
    }
}
