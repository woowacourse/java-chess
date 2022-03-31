package chess.domain.piece;

import static chess.domain.piece.PieceName.KING;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class King extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int MOVABLE_DISTANCE = 1;
    private static final double SCORE = 0;

    public King(Camp camp) {
        super(camp, KING);
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        moveFunction.accept(this);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance + rowDistance == MOVABLE_DISTANCE) {
            return true;
        }
        return columnDistance == MOVABLE_DISTANCE && rowDistance == MOVABLE_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
