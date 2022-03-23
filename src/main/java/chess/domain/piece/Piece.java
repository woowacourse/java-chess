package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {
    private Position position;
    private final Color color;
    private final Name name;

    public Piece(Position position, Color color, String name) {
        this.position = position;
        this.color = color;
        this.name = new Name(color.convertName(name));
    }

    public String getName() {
        return name.getName();
    }
}
