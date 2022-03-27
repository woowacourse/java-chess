package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public static Rank of(String input) {
        return Arrays.stream(values())
            .filter(rank -> rank.index == Integer.parseInt(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 랭크입니다."));
    }

    public static List<Rank> reverseValues() {
        List<Rank> list = new ArrayList<>(List.of(values()));
        Collections.reverse(list);
        return list;
    }

    public int displacement(Rank other) {
        return other.index - this.index;
    }

    public static List<Rank> traceGroup(Rank source, Rank target) {
        return Arrays.stream(values())
            .filter(rank -> rank.isBetween(source, target))
            .collect(Collectors.toList());
    }

    private boolean isBetween(Rank source, Rank target) {
        if (source.compareTo(target) > 0) {
            return this.isBiggerThan(target) && source.isBiggerThan(this);
        }
        return this.isBiggerThan(source) && target.isBiggerThan(this);
    }

    private boolean isBiggerThan(Rank other) {
        return this.compareTo(other) > 0;
    }
}
