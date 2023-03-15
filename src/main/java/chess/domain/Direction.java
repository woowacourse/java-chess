package chess.domain;

public enum Direction {
    PLUS(1),
    MINUS(-1);

    private final int number;

    Direction(int number) {
        this.number = number;
    }


}
