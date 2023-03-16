package chess.piece;

import chess.ChessBoard;
import chess.Position;
import chess.Side;

import java.util.List;
import java.util.Objects;

public abstract class ChessPiece {

    private final Side side;

    public ChessPiece(Side side) {
        this.side = side;
    }

    abstract List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition);

    public String getSide() {
        return side.getSide();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return side == that.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
