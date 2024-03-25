package chess.model.piece;

import chess.model.position.ChessPosition;
import java.util.List;

public abstract class Piece {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    public abstract List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece);

    public boolean isEmpty() {
        return this.side.isEmpty();
    }

    public boolean isSameSide(Piece other) {
        return this.side == other.side;
    }

    public boolean isEnemy(Piece other) {
        return this.side.isEnemy(other.side);
    }

    public void checkValidTargetPiece(Piece other) {
        if (!other.isEmpty() && isSameSide(other)) {
            throw new IllegalArgumentException("타겟 위치에 아군 기물이 존재합니다.");
        }
    }

    public Piece catchTargetPiece(Piece targetPiece) {
        if (isEnemy(targetPiece)) {
            return new Empty();
        }
        return targetPiece;
    }
    public Side getSide() {
        return side;
    }
}
