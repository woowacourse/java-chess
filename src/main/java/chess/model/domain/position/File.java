package chess.model.domain.position;

import chess.model.exception.FileNotFoundException;
import java.util.Map;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final Map<Integer, File> FILE_MAPPER = Map.ofEntries(
            Map.entry(A.value, A), Map.entry(B.value, B), Map.entry(C.value, C), Map.entry(D.value, D),
            Map.entry(E.value, E), Map.entry(F.value, F), Map.entry(G.value, G), Map.entry(H.value, H)
    );
    static final String FILE_NOT_FOUND_MESSAGE = "일치하는 File을 찾을 수 없습니다.";

    private final int value;

    File(final int value) {
        this.value = value;
    }

    static File from(int value) {
        if (!FILE_MAPPER.containsKey(value)) {
            throw new FileNotFoundException();
        }
        return FILE_MAPPER.get(value);
    }

    public int value() {
        return value;
    }

    public int calculateFileGap(final File subtrahend) {
        return value - subtrahend.value;
    }

    public File addValue(final int addition) {
        return from(value + addition);
    }
}
