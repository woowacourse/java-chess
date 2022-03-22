package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {
    private Position position;
    private Color color;

    public Piece(Position position) {
        this.position = position;
    }

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }
}
