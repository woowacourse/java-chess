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

    public final boolean isNotTurn() {
        return this.camp.isNotTurn();
    }

    public final boolean canNotApproach(Position sourcePosition, Position targetPosition) {
        return this.type.canNotApproach(sourcePosition, targetPosition);
    }

    public final double getScore() {
        return this.type.getScore();
    }

    public final Type getType() {
        return this.type;
    }

    public abstract void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    protected abstract boolean canNotMove(Position sourcePosition, Position targetPosition);

    public abstract void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier);

    @Override
    public String toString() {
        return "Piece{" +
                "camp=" + camp +
                ", type=" + type +
                '}';
    }
}
