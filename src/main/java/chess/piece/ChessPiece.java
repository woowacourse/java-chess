package chess.piece;

import chess.Side;

import java.util.Objects;

public abstract class ChessPiece {

    private final Side side;

    public ChessPiece(Side side) {
        this.side = side;
    }

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
