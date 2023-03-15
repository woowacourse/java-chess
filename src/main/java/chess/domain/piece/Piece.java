package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    abstract boolean canMove(Position sourcePosition, Position targetPosition);

    abstract boolean isKing();

    abstract Piece move();

    public boolean isSameTeam(Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }
}
