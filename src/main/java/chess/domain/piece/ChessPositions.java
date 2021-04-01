package chess.domain.piece;

import chess.exception.NotPermittedChessPosition;

import java.util.Arrays;

public enum ChessPositions {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7),
    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String key;
    private final Integer index;

    ChessPositions(final String key, final Integer index) {
        this.key = key;
        this.index = index;
    }

    public static Position parseToPosition(String position) {
        String[] values = position.split("");
        return new Position(get(values[1]), get(values[0]));
    }

    private static int get(String key) {
        return Arrays.stream(values())
                .filter(value -> value.key.equals(key))
                .findAny()
                .orElseThrow(() -> new NotPermittedChessPosition())
                .index;
    }

}
