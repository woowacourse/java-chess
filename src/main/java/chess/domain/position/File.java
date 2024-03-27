package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class File {

    private static final Map<Integer, File> CACHE = new HashMap<>();
    private final int value;

    private File(int value) {
        this.value = value;
    }

    public static File valueOf(int value) {
        if (CACHE.containsKey(value)) {
            return CACHE.get(value);
        }
        CACHE.put(value, new File(value));
        return CACHE.get(value);
    }

    public int subtract(File otherFile) {
        return this.value - otherFile.value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (File) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
