package chess.domain.board.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Xpoint {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String name;
    private final int value;

    Xpoint(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public static List<Xpoint> getRookPoints() {
        return Arrays.asList(A, H);
    }

    public static List<Xpoint> getKnightPoints() {
        return Arrays.asList(B, G);
    }

    public static List<Xpoint> getBishopPoints() {
        return Arrays.asList(C, F);
    }

    public static List<Xpoint> getQueenPoint() {
        return Collections.singletonList(D);
    }

    public static List<Xpoint> getKingPoint() {
        return Collections.singletonList(E);
    }

    public static List<Xpoint> getPawnOrEmptyPoints() {
        return Arrays.asList(values());
    }

    public String getName() {
        return this.name;
    }

    public Xpoint left() {
        return Arrays.stream(values())
                .filter(xpoint -> xpoint.value == this.value - 1)
                .findFirst()
                .orElse(this);
    }

    public Xpoint left(int value) {
        return Arrays.stream(values())
                .filter(xpoint -> xpoint.value == this.value - value)
                .findFirst()
                .orElse(this);
    }

    public Xpoint right() {
        return Arrays.stream(values())
                .filter(xpoint -> xpoint.value == this.value + 1)
                .findFirst()
                .orElse(this);
    }

    public Xpoint right(int value) {
        return Arrays.stream(values())
                .filter(xpoint -> xpoint.value == this.value + value)
                .findFirst()
                .orElse(this);
    }
}
