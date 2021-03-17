package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final Color color;
    private final String notation;
    private Position position;

    public Piece(Color color, Position position, String notation) {
        this.color = color;
        this.position = position;
        this.notation = notation;
    }

    public void move(Position position) {
        this.position = position;
    }

    public String getNotation() {
        return color.changeNotation(notation);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", notation='" + notation + '\'' +
                '}';
    }
}
