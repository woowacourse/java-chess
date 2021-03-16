package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private static int BLANK_LINE_ROW_PIVOT = 2;
    private static int NUMBER_OF_BLANK_RANKS = 4;

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

    public static List<Rank> getBlankRanks() {
        return Arrays.stream(values())
                .skip(BLANK_LINE_ROW_PIVOT)
                .limit(NUMBER_OF_BLANK_RANKS)
                .collect(Collectors.toList());
    }

    public String getLetter() {
        return this.letter;
    }
}
