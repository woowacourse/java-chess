package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(String input) {
        return Arrays.stream(values())
            .filter(rank -> rank.value == Integer.parseInt(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 랭크입니다."));
    }

    public static List<Rank> reverseValues() {
        List<Rank> list = new ArrayList<>(List.of(values()));
        Collections.reverse(list);
        return list;
    }
}
