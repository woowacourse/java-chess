package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public abstract class ChessPiece {

    protected final Color color;
    protected final Shape shape;

    protected ChessPiece(Color color) {
        this.color = color;
        this.shape = selectShape(color);
    }

    abstract Shape selectShape(Color color);

    public String getShape() {
        return shape.getShape();
    }

    //    private final Side side;
//
//    public ChessPiece(Side side) {
//        this.side = side;
//    }
//
//    public String getSide() {
//        return side.getSide();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ChessPiece that = (ChessPiece) o;
//        return side == that.side;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(side);
//    }
}
