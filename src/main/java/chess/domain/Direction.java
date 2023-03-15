package chess.domain;

public enum Direction {
    PLUS(1),
    ZERO(0),
    MINUS(-1);

    private final int number;

    Direction(int number) {
        this.number = number;
    }


}
