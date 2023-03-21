package chess.domain.position;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    ;

    private static final int START_EXCLUSIVE = 1;

    private final String command;
    private final int position;

    Rank(final String command, final int position) {
        this.command = command;
        this.position = position;
    }

    private static Rank from(final int position) {
        return Arrays.stream(values())
                .filter(value -> value.position == position)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치 값은 1 ~ 8 사이의 값이어야 합니다."));
    }

    public List<Rank> between(final Rank rank) {
        final List<Rank> result = IntStream.range(min(position, rank.position), max(position, rank.position))
                .skip(START_EXCLUSIVE)
                .mapToObj(Rank::from)
                .collect(toList());
        if (position > rank.position) {
            Collections.reverse(result);
        }
        return result;
    }

    public int calculateGap(final Rank target) {
        return position - target.position;
    }

    public String command() {
        return command;
    }
}
