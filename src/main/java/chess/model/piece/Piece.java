package chess.model.piece;

import chess.model.position.ChessPosition;
import java.util.List;

public abstract class Piece {
    protected final Side side;

    protected Piece(final Side side) {
        this.side = side;
    }

    public abstract List<ChessPosition> findPath(
            final ChessPosition source, final ChessPosition target, final Piece targetPiece
    );

    public boolean isEmpty() {
        return this.side.isEmpty();
    }

    public boolean isSameSide(final Piece other) {
        return this.side == other.side;
    }

    public boolean isEnemy(final Piece other) {
        return this.side.isEnemy(other.side);
    }

    public void checkValidTargetPiece(final Piece other) {
        if (!other.isEmpty() && isSameSide(other)) {
            throw new IllegalArgumentException("타겟 위치에 아군 기물이 존재합니다.");
        }
    }

    public Piece catchTargetPiece(final Piece targetPiece) {
        if (isEnemy(targetPiece)) {
            return new Empty();
        }
        return targetPiece;
    }

    public Side getSide() {
        return side;
    }
}
