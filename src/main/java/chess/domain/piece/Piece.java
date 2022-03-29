package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public abstract class Piece {
    private final Camp camp;
    private final Type type;

    protected Piece(Camp camp, Type type) {
        this.camp = camp;
        this.type = type;
    }

    public final boolean isCamp(Camp camp) {
        return this.camp == camp;
    }

    public final boolean isSameCampWith(Piece piece) {
        return this.isCamp(piece.camp);
    }

    public final boolean isType(Type type) {
        return this.type == type;
    }

    public abstract void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    protected abstract boolean canMove(Position sourcePosition, Position targetPosition);

    public abstract void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    public abstract double getScore();
}
