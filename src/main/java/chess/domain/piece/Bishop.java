package chess.domain.piece;

import static chess.domain.piece.PieceName.BISHOP;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Bishop extends NotNullPiece {

    private static final int SCORE = 3;
    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";

    public Bishop(Camp camp) {
        super(camp, BISHOP);
    }

    @Override
    public void move(Position beforePosition,
                     Position afterPosition,
                     Consumer<Piece> moveFunction) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        moveFunction.accept(this);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        this.move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        return columnDistance == rowDistance;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
