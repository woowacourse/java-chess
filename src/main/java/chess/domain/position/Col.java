package chess.domain.position;

import chess.exception.InvalidCoordinateException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Col {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String col;
    private final int location;

    Col(final String col, final int location) {
        this.col = col;
        this.location = location;
    }

    public static int location(final String col) {
        return Arrays.stream(Col.values())
                .filter(value -> value.col.equals(col))
                .findFirst()
                .orElseThrow(InvalidCoordinateException::new)
                .location;
    }

    public static String initial(final int location) {
        return Arrays.stream(Col.values())
                .filter(value -> value.location == location)
                .findFirst()
                .orElseThrow(InvalidCoordinateException::new)
                .col;
    }

    public static List<Integer> pawnInitCols() {
        return Arrays.stream(Col.values())
                .map(pawnCol -> pawnCol.location)
                .collect(Collectors.toList());
    }

    public static List<Integer> rookInitCols() {
        return Arrays.asList(A.location, H.location);
    }

    public static List<Integer> knightInitCols() {
        return Arrays.asList(B.location, G.location);
    }

    public static List<Integer> bishopInitCols() {
        return Arrays.asList(C.location, F.location);
    }

    public static List<Integer> queenInitCols() {
        return Collections.singletonList(D.location);
    }

    public static List<Integer> kingInitCols() {
        return Collections.singletonList(E.location);
    }
}
