package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public class None extends Piece {
    private static final String ERROR_CANT_MOVE = "말이 없어 이동할 수 없습니다.";

    public None() {
        super(Camp.NONE, Type.NONE);
    }

    @Override
    public void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        throw new IllegalArgumentException(ERROR_CANT_MOVE);
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
