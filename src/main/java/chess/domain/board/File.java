package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum File {

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
    private static Map<File, File> opposite;

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

    File(String name) {
        this.name = name;
    }

    public File opposite() {
        return opposite.get(this);
    }

    public Optional<File> jump(int index) {
        try {
            return Optional.of(values()[ordinal() + index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public Optional<File> next() {
        return jump(NEXT_INDEX);
    }

    public Optional<File> previous() {
        return jump(PREVIOUS_INDEX);
    }

    public String getName() {
        return name;
    }
}
