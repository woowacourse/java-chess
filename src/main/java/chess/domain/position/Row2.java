package chess.domain.position;

public enum Row2 {

    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2'),
    ONE('1');

    private final char value;

    Row2(final char value) {
        this.value = value;
    }

//    public Row2 goRight() {
//
//    }
//
//    public Row2 goLeft() {
//
//    }

    private boolean isLeftBoundary() {
        return ONE.equals(this);
    }

    private boolean isRightBoundary() {
        return EIGHT.equals(this);
    }

    public String value() {
        return String.valueOf(value);
    }
}
