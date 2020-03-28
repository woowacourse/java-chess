package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private char representation;
    private Position position;

    public Piece(char representation, Position position) {
        this.representation = representation;
        this.position = position;
    }

    public char getRepresentation() {
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.valueOf(representation);
    }
}
