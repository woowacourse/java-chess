package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    private boolean isFirstMove;

    public Piece(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }

    public Piece() {
        isFirstMove = true;
    }

    public abstract boolean isMovable(final Position current, final Position destination,
                                      final Map<Position, Piece> chessBoard);

    public abstract boolean checkPositionRule(final Position current, final Position destination);

    public abstract boolean isKing();

    public abstract boolean isRook();

    public abstract boolean isPawn();

    public final boolean checkEmptyPath(final List<Position> path, final Map<Position, Piece> chessBoard) {
        return path.stream()
                .noneMatch(chessBoard::containsKey);
    }

    public final void moved() {
        isFirstMove = false;
    }

    public final boolean isFirstMove() {
        return isFirstMove;
    }
}
