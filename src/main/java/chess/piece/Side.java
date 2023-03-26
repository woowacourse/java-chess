package chess.piece;

import java.util.stream.Stream;

public enum Side {

    BLACK,
    WHITE,
    EMPTY;

    public static Side convertSide(String input) {
        return Stream.of(Side.values())
                .filter(side -> side.name().equals(input))
                .findFirst()
                .orElse(null);
    }
}
