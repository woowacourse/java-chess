package chess.domain.piece;

import static chess.domain.piece.PieceName.BISHOP;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
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
                     Consumer<Piece> movePiece) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public void move(final Positions positions,
                     final Consumer<Piece> movePiece) {
        if (!canMove(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        return columnDistance == rowDistance;
    }

    private boolean canMove(final Positions positions) {
        int columnDistance = positions.calculateColumnDistance();
        int rowDistance = positions.calculateRowDistance();
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
