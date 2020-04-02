package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

public enum Column {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H");

    private static final int NEXT_INDEX = 1;
    private static final int PREVIOUS_INDEX = -1;
    private static final Map<Column, Column> opposite;

    static {
        opposite = new HashMap<>();
        opposite.put(A, H);
        opposite.put(B, G);
        opposite.put(C, F);
        opposite.put(D, E);
        opposite.put(E, D);
        opposite.put(F, C);
        opposite.put(G, B);
        opposite.put(H, A);
    }

    private String name;

    Column(String name) {
        this.name = name;
    }

    public Column opposite() {
        return opposite.get(this);
    }

    public Column jump(int index) {
            return values()[ordinal() + index];
    }

    public Column next() {
        return jump(NEXT_INDEX);
    }

    public Column previous() {
        return jump(PREVIOUS_INDEX);
    }

    public String getName() {
        return name;
    }
}
