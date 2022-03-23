package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final Position position;

    public Piece(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getSymbol();
}
