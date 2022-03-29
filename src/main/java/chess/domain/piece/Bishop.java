package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Bishop extends Piece {
    private static final String ERROR_CANT_MOVE = "비숍이 이동할 수 없는 위치입니다.";

    public Bishop(Camp camp) {
        super(camp, Type.BISHOP);
    }

    @Override
    public void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        if (canNotMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(ERROR_CANT_MOVE);
        }
        moveApplier.accept(this);
    }

    @Override
    protected boolean canNotMove(Position sourcePosition, Position targetPosition) {
        return super.canNotApproach(sourcePosition, targetPosition);
    }

    @Override
    public void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        this.move(sourcePosition, targetPosition, moveApplier);
    }
}
