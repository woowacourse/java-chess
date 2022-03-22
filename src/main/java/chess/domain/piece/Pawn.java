package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Pawn {
    private final Position position;
    private final Color color;

    public Pawn(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public String signature() {
        return color.correctSignature("p");
    }
}
