package chess.domain.point;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private static final Map<Integer, Rank> CACHED_RANK = Arrays.stream(values())
            .collect(Collectors.toMap(rank -> rank.position, Function.identity()));

    private final int position;

    Rank(final int position) {
        this.position = position;
    }

    public static Rank from(final int position) {
        if (CACHED_RANK.containsKey(position)) {
            return CACHED_RANK.get(position);
        }
        throw new IllegalArgumentException("세로 위치의 범위를 벗어났습니다. 입력된 세로 위치 = " + position);
    }

    public boolean canMove(final int distanceToMove) {
        final int movedPosition = addPosition(distanceToMove);
        return CACHED_RANK.containsKey(movedPosition);
    }

    public int addPosition(final int distanceToMove) {
        return position + distanceToMove;
    }

    public int calculateDistanceFrom(final Rank rank) {
        return rank.subtractPosition(position);
    }

    private int subtractPosition(final int position) {
        return this.position - position;
    }

    public int getPosition() {
        return position;
    }
}
