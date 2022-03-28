package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public final boolean isCamp(Camp camp) {
        return this.camp == camp;
    }

    public final boolean isSameCampWith(Piece piece) {
        return this.isCamp(piece.camp);
    }

    public abstract void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    protected abstract boolean canMove(Position sourcePosition, Position targetPosition);

    public abstract void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    public abstract boolean isBishop();

    public abstract boolean isKing();

    public abstract boolean isKnight();

    public abstract boolean isPawn();

    public abstract boolean isQueen();

    public abstract boolean isRook();

    public abstract boolean isNone();

    public abstract double getScore();
}
