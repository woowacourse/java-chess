package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Queen extends Piece {
    private static final String ERROR_CANT_MOVE = "퀸이 이동할 수 없는 위치입니다.";

    public Queen(Camp camp) {
        super(camp, Type.QUEEN);
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
