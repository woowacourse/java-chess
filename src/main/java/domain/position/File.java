package domain.position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private static final Map<Integer, File> FILE_POOL = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(
                file -> FILE_POOL.put(file.index, file)
        );
    }

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static File of(final int index) {
        if (!FILE_POOL.containsKey(index)) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다.");
        }
        return FILE_POOL.get(index);
    }
}
