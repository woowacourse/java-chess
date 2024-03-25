package chess.model.piece;

import chess.model.position.Movement;

public abstract class Piece {
    private static final String PIECE_NAME_DELIMITER = "_";

    private final Color color;
    private final Type type;

    Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isValid(Movement movement);

    public boolean isType(Type type) {
        return this.type == type;
    }

    public boolean isEmpty() {
        return isType(Type.NONE);
    }

    public boolean isSameColorWith(Piece piece) {
        return color == piece.color;
    }

    public boolean isNotSameColor(Color color) {
        return this.color != color;
    }

    public String getName() {
        return color.name() + PIECE_NAME_DELIMITER + type.name();
    }
}
