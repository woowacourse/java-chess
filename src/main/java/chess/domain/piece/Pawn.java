package chess.domain.piece;

import static chess.domain.piece.PieceProperty.PAWN;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.function.Consumer;

public final class Pawn extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int MOVABLE_DISTANCE_AT_FIRST_TURN = 2;
    private static final int MOVABLE_DISTANCE = 1;
    private static final int DIRECTION_CRITERIA = 0;
    private static final int NO_DISTANCE = 0;

    private boolean firstMove;

    public Pawn(Camp camp) {
        super(camp, PAWN);
        this.firstMove = true;
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> movePiece) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
        firstMove = false;
    }

    @Override
    public void move(final Positions positions, final Consumer<Piece> movePiece) {
        if (!canMove(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
        firstMove = false;
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int rowDirectedDistance = afterPosition.rowDirectedDistance(beforePosition);
        int columnDistance = afterPosition.columnDistance(beforePosition);
        if (columnDistance != NO_DISTANCE) {
            return false;
        }
        if (firstMove) {
            return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE_AT_FIRST_TURN);
        }
        return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE);
    }

    private boolean canMove(final Positions positions) {
        int rowDirectedDistance = positions.calculateDirectedRowDistance();
        int columnDistance = positions.calculateColumnDistance();
        if (columnDistance != NO_DISTANCE) {
            return false;
        }
        if (firstMove) {
            return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE_AT_FIRST_TURN);
        }
        return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        if (!canCapture(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        moveFunction.accept(this);
        firstMove = false;
    }

    @Override
    public void capture(final Positions positions, final Consumer<Piece> moveFunction) {
        if (!canCapture(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        moveFunction.accept(this);
        firstMove = false;
    }

    private boolean canCapture(Position beforePosition, Position afterPosition) {
        int columnDistance = afterPosition.columnDistance(beforePosition);
        int rowDistance = afterPosition.rowDirectedDistance(beforePosition);
        return columnDistance == MOVABLE_DISTANCE && checkMovableLimitByCamp(rowDistance, MOVABLE_DISTANCE);
    }

    private boolean canCapture(final Positions positions) {
        int columnDistance = positions.calculateColumnDistance();
        int rowDistance = positions.calculateDirectedRowDistance();
        return columnDistance == MOVABLE_DISTANCE && checkMovableLimitByCamp(rowDistance, MOVABLE_DISTANCE);
    }

    private boolean checkMovableLimitByCamp(int distance, int movableDistance) {
        if (isBlack()) {
            return -movableDistance <= distance && distance < DIRECTION_CRITERIA;
        }
        return DIRECTION_CRITERIA < distance && distance <= movableDistance;
    }
}
