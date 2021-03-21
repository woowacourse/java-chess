package chess.domain.position;

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

    final String col;
    final int location;

    Col(final String col, final int location) {
        this.col = col;
        this.location = location;
    }

    public static int getLocation(final String col) {
        return Arrays.stream(Col.values())
                .filter(value -> value.col.equals(col))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 좌표입니다."))
                .location;
    }

    public static List<Integer> getPawnInitCols() {
        return Arrays.stream(Col.values())
                .map(pawnCol -> pawnCol.location)
                .collect(Collectors.toList());
    }

    public static List<Integer> getRookInitCols() {
        return Arrays.asList(A.location, H.location);
    }

    public static List<Integer> getKnightInitCols() {
        return Arrays.asList(B.location, G.location);
    }

    public static List<Integer> getBishopInitCols() {
        return Arrays.asList(C.location, F.location);
    }

    public static List<Integer> getQueenInitCols() {
        return Collections.singletonList(D.location);
    }

    public static List<Integer> getKingInitCols() {
        return Collections.singletonList(E.location);
    }
}
