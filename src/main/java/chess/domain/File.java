package chess.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private static final Map<String, File> searchMap;

    private final String letter;

    static {
        searchMap = Arrays.stream(values())
                .collect(toMap(value -> value.letter, Function.identity()));
    }

    File(String letter) {
        this.letter = letter;
    }

    public static File from(String letter) {
        return Objects.requireNonNull(searchMap.get(letter), "해당하는 문자의 File이 없습니다.");
    }
}
