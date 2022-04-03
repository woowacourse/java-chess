package chess.domain.piece;

import static chess.domain.board.BoardFactory.BLACK_PAWN_INITIAL_ROW;
import static chess.domain.board.BoardFactory.WHITE_PAWN_INITIAL_ROW;
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

    public Pawn(Camp camp) {
        super(camp, PAWN);
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
    public boolean canMove(Position beforePosition, Position afterPosition) {
        return canMove(new Positions(beforePosition, afterPosition));
    }

    private boolean isFirstMove(final Position beforePosition) {
        if (isSameCampWith(Camp.WHITE)) {
            return beforePosition.isSameRow(WHITE_PAWN_INITIAL_ROW);
        }
        return beforePosition.isSameRow(BLACK_PAWN_INITIAL_ROW);
    }

    @Override
    public boolean canMove(final Positions positions) {
        int rowDirectedDistance = positions.calculateDirectedRowDistance();
        int columnDistance = positions.calculateColumnDistance();

        if (columnDistance == Math.abs(rowDirectedDistance)) {
            return canCapture(positions);
        }

        if (columnDistance != NO_DISTANCE) {
            return false;
        }
        if (isFirstMove(positions.before())) {
            return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE_AT_FIRST_TURN);
        }
        return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE);
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
