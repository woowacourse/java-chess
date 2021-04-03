package chess.domain.position;

import chess.exception.DomainException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final Map<String, File> searchMap;

    private final String letter;
    private final int coordinate;

    static {
        searchMap = Arrays.stream(values())
                .collect(toMap(value -> value.letter, Function.identity()));
    }

    File(String letter, int coordinate) {
        this.letter = letter;
        this.coordinate = coordinate;
    }

    public static File from(String letter) {
        return Objects.requireNonNull(searchMap.get(letter), "해당하는 문자의 File이 없습니다.");
    }

    public static File from(int coordinate) {
        return Arrays.stream(values())
                .filter(file -> file.coordinate == coordinate)
                .findAny()
                .orElseThrow(() -> new DomainException("해당하는 좌표의 File이 없습니다."));
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
