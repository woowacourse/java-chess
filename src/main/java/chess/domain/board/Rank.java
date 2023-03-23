package chess.domain.board;

import chess.domain.piece.Team;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank의 index는 1~8이여야합니다."));
    }

    public static Rank from(final Team team, final boolean isPawn) {
        if (team == Team.WHITE) {
            if (isPawn) {
                return TWO;
            }
            return ONE;
        }

        if (team == Team.BLACK) {
            if (isPawn) {
                return SEVEN;
            }
            return EIGHT;
        }
        throw new IllegalArgumentException("올바르지 않은 팀입니다.");
    }

    public static int calculateInterval(final Rank from, final Rank to) {
        return Math.abs(from.index - to.index);
    }

    public static List<Rank> sliceBetween(final Rank from, final Rank to) {
        final int min = Math.min(from.index, to.index);
        final int max = Math.max(from.index, to.index);

        return IntStream.rangeClosed(min, max)
                .mapToObj(Rank::of)
                .collect(Collectors.toList());
    }

    public static List<Rank> sliceBetweenExcludeEnd(final Rank from, final Rank to) {
        final int min = Math.min(from.index, to.index);
        final int max = Math.max(from.index, to.index);

        return IntStream.range(min, max)
                .skip(1)
                .mapToObj(Rank::of)
                .collect(Collectors.toList());
    }

    public int getIndex() {
        return index;
    }
}
