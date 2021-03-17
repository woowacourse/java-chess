package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final String notation;
    private Position position;

    public Piece(Position position, String notation) {
        this.position = position;
        this.notation = notation;
    }

    public void move(Position position) {
        this.position = position;
    }

    public String getNotation() {
        return notation;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "notation='" + notation + '\'' +
                ", position=" + position +
                '}';
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }
}
