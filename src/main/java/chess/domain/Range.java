package chess.domain;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public enum Range {
    ROOK_RANGE(Arrays.asList(NORTH, EAST, SOUTH, WEST), 7),
    BISHOP_RANGE(Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST), 7),
    QUEEN_RANGE(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST), 7),
    KING_RANGE(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST), 1),
    KNIGHT_RANGE(Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS), 2),

    BLACK_START_PAWN_RANGE(Arrays.asList(SOUTH), 2),
    BLACK_PAWN_RANGE(Arrays.asList(SOUTH), 1),
    BLACK_PAWN_ATTACK_RANGE(Arrays.asList(SOUTHEAST, SOUTHWEST), 1),

    WHITE_START_PAWN_RANGE(Arrays.asList(NORTH), 2),
    WHITE_PAWN_RANGE(Arrays.asList(NORTH), 1),
    WHITE_PAWN_ATTACK_RANGE(Arrays.asList(NORTHEAST, NORTHWEST), 1);

    private final List<Direction> directions;
    private final int max;

    Range(List<Direction> directions, int max) {
        this.directions = directions;
        this.max = max;
    }

    public Direction isAvailable(int columnDiff, int rowDiff) {
        int distance = Math.max(Math.abs(columnDiff), Math.abs(rowDiff));
        if (distance > max) {
            throw new IllegalArgumentException("거리 초과");
        }

        return directions.stream()
                .filter(d -> d.isSameDirection(columnDiff, rowDiff))
                .findAny()
                .orElseThrow(() ->new IllegalArgumentException("불가능한 방향"))
                ;
    }
}
