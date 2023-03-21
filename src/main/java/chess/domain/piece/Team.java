package chess.domain.piece;

import chess.domain.board.RankCoordinate;

import java.util.Arrays;
import java.util.List;

import static chess.domain.board.RankCoordinate.*;

public enum Team {
    BLACK(List.of(SEVEN, EIGHT)),
    WHITE(List.of(ONE, TWO)),
    EMPTY(List.of(THREE, FOUR, FIVE, SIX));

    private final List<RankCoordinate> rankCoordinates;

    Team(List<RankCoordinate> rankCoordinates) {
        this.rankCoordinates = rankCoordinates;
    }

    public static Team of(RankCoordinate rankCoordinate) {
        return Arrays.stream(values())
                .filter(team -> team.rankCoordinates.contains(rankCoordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 행 번호를 입력해주세요."));
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
        if (this == BLACK) {
            return Direction.DOWN.getDirection();
        }
        return Direction.UP.getDirection();
    }
}
