package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class King extends Piece {
    private static final String ERROR_CANT_MOVE = "킹이 이동할 수 없는 위치입니다.";
    private static final double SCORE = 0;

    public King(Camp camp) {
        super(camp, Type.KING);
    }

    @Override
    public void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        if (!canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(ERROR_CANT_MOVE);
        }
        moveApplier.accept(this);
    }

    @Override
    protected boolean canMove(Position sourcePosition, Position targetPosition) {
        return super.canApproach(sourcePosition, targetPosition);
    }

    @Override
    public void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        this.move(sourcePosition, targetPosition, moveApplier);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
