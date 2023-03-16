package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position sourcePosition, Position targetPosition, Color color);

    public abstract boolean isEmpty();

    public abstract Piece move();

    public boolean isSameTeam(Color color) {
        return this.color == color;
    }

    protected boolean isNotMyPosition(Position sourcePosition, Position targetPosition) {
        return !sourcePosition.equals(targetPosition);
    }

    protected boolean isDiagonal(Position sourcePosition, Position targetPosition) {
        int columnAbs = Math.abs(sourcePosition.getColumn() - targetPosition.getColumn());
        int rowAbs = Math.abs(sourcePosition.getRow() - targetPosition.getRow());
        return columnAbs == rowAbs;
    }

    protected boolean isStraight(Position sourcePosition, Position targetPosition) {
        return (sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate()
                || sourcePosition.getRankCoordinate() == targetPosition.getRankCoordinate());
    }

    protected boolean isNotSameColor(Color color) {
        return this.color != color;
    }

    public Color getColor() {
        return color;
    }
}
