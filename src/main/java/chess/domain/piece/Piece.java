package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {

    private final Color color;
    private final String name;
    private final Position position;

    protected Piece(Color color, String name, Position position) {
        this.color = color;
        this.name = name;
        this.position = position;
    }

    public final Position getPosition() {
        return position;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }
}
