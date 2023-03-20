package chess.piece;

import java.util.Objects;

public class ChessPiece {

    private final Shape shape;
    private final Side side;

    public ChessPiece(Shape shape, Side side) {
        this.shape = shape;
        this.side = side;
    }

    public String getName() {
        if (side.equals(Side.BLACK)) {
            return shape.getBlackName();
        }
        return shape.getWhiteName();
    }

    public Shape getShape() {
        return shape;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return shape == that.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape);
    }

}
