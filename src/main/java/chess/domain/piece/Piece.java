package chess.domain.piece;

import chess.domain.piece.position.Position;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    abstract public String display();

    public Position getPosition() {
        return position;
    }
}
