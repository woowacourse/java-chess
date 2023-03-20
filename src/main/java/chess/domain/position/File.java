package chess.domain.position;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);
    public static final String INVALID_FILE_ERROR_MESSAGE = "잘못된 좌표입니다.";

    private final String label;
    private final int index;
    
    File(final String label, final int index) {
        this.label = label;
        this.index = index;
    }
    
    static File findByLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_ERROR_MESSAGE));
    }
    
    public static File findByIndex(final int index) {
        return Arrays.stream(values())
                .filter(value -> value.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_ERROR_MESSAGE));
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public int getIndex() {
        return this.index;
    }
}
