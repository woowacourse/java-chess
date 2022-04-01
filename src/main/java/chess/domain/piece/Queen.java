package chess.domain.piece;

import static chess.domain.piece.PieceName.QUEEN;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.function.Consumer;

public final class Queen extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int NOT_MOVED_DISTANCE = 0;
    private static final double SCORE = 9;

    public Queen(Camp camp) {
        super(camp, QUEEN);
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> movePiece) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public void move(final Positions positions, final Consumer<Piece> movePiece) {
        if (!canMove(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance == NOT_MOVED_DISTANCE) {
            return true;
        }
        if (rowDistance == NOT_MOVED_DISTANCE) {
            return true;
        }
        return columnDistance == rowDistance;
    }

    private boolean canMove(final Positions positions) {
        int columnDistance = positions.before().columnDistance(positions.after());
        int rowDistance = positions.before().rowDistance(positions.after());
        if (columnDistance == NOT_MOVED_DISTANCE) {
            return true;
        }
        if (rowDistance == NOT_MOVED_DISTANCE) {
            return true;
        }
        return columnDistance == rowDistance;
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    public void capture(final Positions positions, final Consumer<Piece> moveFunction) {
        move(positions, moveFunction);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
