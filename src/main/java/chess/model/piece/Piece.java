package chess.model.piece;

import chess.model.position.ChessPosition;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    public abstract String getText();
    public abstract List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece);

    protected final boolean isSameSide(Piece other) {
        return this.side == other.side;
    }

    protected final void checkValidTargetPiece(Piece other) {
        if (!other.equals(Blank.INSTANCE) && isSameSide(other)) {
            throw new IllegalArgumentException("타겟 위치에 아군 기물이 존재합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
