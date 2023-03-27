package chess.piece;

import chess.ChessBoard;
import chess.position.MovablePosition;
import chess.position.Position;
import java.util.Arrays;
import java.util.Objects;

public abstract class ChessPiece {

    private final Side side;

    public ChessPiece(Side side) {
        this.side = side;
    }

    public abstract MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition);

    public boolean isEqualSide(ChessPiece chessPiece) {
        return side.equals(chessPiece.getSide());
    }

    public String getName() {
        Shape pieceShape = this.getShape();
        if (side.equals(Side.BLACK)) {
            return pieceShape.getBlackName();
        }
        return pieceShape.getWhiteName();
    }

    public Shape getShape() {
        String shapeName = Arrays.asList(this.getClass().getName().split("\\.")).get(2).toUpperCase();
        return Shape.findShape(shapeName);
    }

    public Side getSide() {
        return side;
    }

    public double getScore(Side compareSide) {
        if (compareSide.equals(side)) {
            return this.getShape().getScore();
        }
        return 0;
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
        return side == that.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
