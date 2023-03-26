package chess.domain.piece;

import chess.domain.*;

import java.util.Objects;

public abstract class ChessPiece {

    protected final Color color;
    protected final Shape shape;

    protected ChessPiece(Color color) {
        this.color = color;
        this.shape = selectShape(color);
    }

    abstract Shape selectShape(Color color);

    abstract Direction findMovableDirection(Position sourcePosition, Position targetPosition);

    abstract int findDistance(Direction direction, Position sourcePosition, Position targetPosition);

    abstract boolean isMovable(Movement movement, ChessBoard chessBoard);

    public String getShape() {
        return shape.getShape();
    }

    public Color getColor() {
        return color;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean hasSameColor(ChessPiece chessPiece) {
        return this.color.equals(chessPiece.getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color && shape == that.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, shape);
    }
}
