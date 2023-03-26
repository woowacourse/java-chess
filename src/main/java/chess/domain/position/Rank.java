package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);
    
    private final String label;
    private final int index;
    
    Rank(String label, int index) {
        this.label = label;
        this.index = index;
    }
    
    public static Rank findByLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌표입니다."));
    }
    
    public static Rank findByIndex(int index) {
        return Arrays.stream(values())
                .filter(value -> value.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌표입니다."));
    }
    
    public String getLabel() {
        return label;
    }
    
    public int getIndex() {
        return index;
    }
}
