package chess.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private static final Map<String, Rank> searchMap;

    private final String letter;

    static {
        searchMap = Arrays.stream(values())
                .collect(toMap(value -> value.letter, Function.identity()));
    }

    Rank(String letter) {
        this.letter = letter;
    }

    public static Rank from(String letter) {
        return Objects.requireNonNull(searchMap.get(letter), "해당하는 문자의 Rank가 없습니다.");
    }
}
