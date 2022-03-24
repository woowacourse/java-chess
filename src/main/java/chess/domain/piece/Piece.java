package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public final boolean isBlack() {
        return this.camp == Camp.BLACK;
    }

    public abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract boolean isBishop();

    public abstract boolean isKing();

    public abstract boolean isKnight();

    public abstract boolean isPawn();

    public abstract boolean isQueen();

    public abstract boolean isRook();
}
