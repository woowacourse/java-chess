package chess.domain.piece;

import static chess.domain.piece.PieceName.ROOK;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Rook extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int NOT_MOVED_DISTANCE = 0;
    private static final double SCORE = 5;

    public Rook(Camp camp) {
        super(camp, ROOK);
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
        this.move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance == NOT_MOVED_DISTANCE) {
            return true;
        }
        return rowDistance == NOT_MOVED_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
