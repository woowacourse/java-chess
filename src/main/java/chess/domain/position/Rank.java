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
    
    public static final String INVALID_RANK_ERROR_MESSAGE = "[POSITION ERROR] 잘못된 좌표입니다.";
    private final String label;
    private final int index;
    
    Rank(String label, int index) {
        this.label = label;
        this.index = index;
    }
    
    
    public static Rank findByIndex(int index) {
        return Arrays.stream(values())
                .filter(value -> value.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_ERROR_MESSAGE));
    }
    
    public String getLabel() {
        return this.label;
    }
    
    
    public int getIndex() {
        return this.index;
    }
}
