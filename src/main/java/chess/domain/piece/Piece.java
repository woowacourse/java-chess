package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public final boolean isBlack() {
        return this.camp == Camp.BLACK;
    }

    public abstract void move(Position beforePosition,
                              Position afterPosition,
                              Consumer<Piece> moveFunction);

    public abstract void capture(Position beforePosition,
                                 Position afterPosition,
                                 Consumer<Piece> moveFunction);

    public boolean isSameCampWith(Piece targetPiece) {
        return this.camp == targetPiece.camp;
    }

    protected abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract boolean isBishop();

    public abstract boolean isKing();

    public abstract boolean isKnight();

    public abstract boolean isPawn();

    public abstract boolean isQueen();

    public abstract boolean isRook();
}
