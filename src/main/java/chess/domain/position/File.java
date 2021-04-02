package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final Map<String, File> SEARCH_MAP;

    static {
        SEARCH_MAP = Arrays.stream(values())
                .collect(toMap(value -> value.letter, Function.identity()));
    }

    private final String letter;
    private final int coordinate;

    File(String letter, int coordinate) {
        this.letter = letter;
        this.coordinate = coordinate;
    }

    public static File from(String letter) {
        return Optional.ofNullable(SEARCH_MAP.get(letter))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 문자의 File이 없습니다."));
    }

    public static File from(int coordinate) {
        return Arrays.stream(values())
                .filter(file -> file.coordinate == coordinate)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 좌표의 File이 없습니다."));
    }

    public String getLetter() {
        return this.letter;
    }

    public int calculateGapAsInt(File thatFile) {
        return thatFile.coordinate - this.coordinate;
    }

    public File add(int xDegree) {
        return File.from(this.coordinate + xDegree);
    }
}
