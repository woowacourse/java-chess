package chess.model.square;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int value;

    File(int value) {
        this.value = value;
    }

    public static File findFileByName(String rawFile) {
        return Arrays.stream(values())
                .filter(file -> file.name().toLowerCase(Locale.ROOT).equals(rawFile))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File 입니다: " + rawFile));
    }

    public static File findFileByValue(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File 입니다: " + value));
    }

    public boolean availableLocation(int distance) {
        return Arrays.stream(File.values())
                .anyMatch(file -> file.value == (this.value + distance));
    }

    public File nextTo(Integer value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == (this.value + value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File 입니다: " + value));
    }

    public int countPawnsInSameFile(Set<Square> blackPawns) {
        return (int) blackPawns.stream()
                .filter(square -> square.sameFile(this))
                .count();
    }

    public int value() {
        return value;
    }
}
