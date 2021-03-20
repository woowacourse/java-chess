package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    protected boolean isMoved = false;

    public abstract boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard);

    public abstract boolean checkPositionRule(final Position current, final Position destination);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public final boolean checkEmptyPath(final List<Position> path, final Map<Position, Piece> chessBoard) {
        return path.stream()
                .noneMatch(chessBoard::containsKey);
    }

    public void isMoved() {
        this.isMoved = true;
    }
}
