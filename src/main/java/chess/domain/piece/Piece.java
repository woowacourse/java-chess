package chess.domain.piece;

import chess.domain.Color;

public abstract class Piece {
    protected Position position;
    protected String name;
    protected Color color;

    public Piece(Position position, String name, Color color) {
        this.position = position;
        this.name = name;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    abstract void move(Position target, CurrentPieces currentPieces);
}
