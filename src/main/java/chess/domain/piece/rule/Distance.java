package chess.domain.piece.rule;

import java.util.Arrays;

public enum Distance {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    private int value;

    Distance(final int value){
        this.value = value;
    }

    public boolean isBelow(final Distance other){
        return this.value <= other.value;
    }

    public int getValue() {
        return value;
    }

    public Distance before(){
        return Arrays.stream(values())
                .filter(value -> value.value == this.value-1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Distance next(){
        return Arrays.stream(values())
                .filter(value -> value.value == this.value+1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
