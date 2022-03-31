package chess.domain.piece;

import chess.domain.piece.property.Color;

public class Name {
    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name, Color color) {
        if (color == Color.Black) {
            return new Name(name.toUpperCase());
        }

        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
