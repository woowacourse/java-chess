package chess.domain.piece;

import static chess.domain.board.BoardFactory.BLACK_PAWN_INITIAL_ROW;
import static chess.domain.board.BoardFactory.WHITE_PAWN_INITIAL_ROW;
import static chess.domain.board.UnitDirectVector.BOTTOM;
import static chess.domain.board.UnitDirectVector.BOTTOM_LEFT;
import static chess.domain.board.UnitDirectVector.BOTTOM_RIGHT;
import static chess.domain.board.UnitDirectVector.TOP;
import static chess.domain.board.UnitDirectVector.TOP_LEFT;
import static chess.domain.board.UnitDirectVector.TOP_RIGHT;
import static chess.domain.piece.PieceProperty.PAWN;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import java.util.List;
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
        if (!checkCanMoveByDistance(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public void move(final Positions positions, final Consumer<Piece> movePiece) {
        if (!checkCanMoveByDistance(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public boolean checkCanMoveByDistance(Position beforePosition, Position afterPosition) {
        return checkCanMoveByDistance(new Positions(beforePosition, afterPosition));
    }

    private boolean isFirstMove(final Position beforePosition) {
        if (isSameCampWith(Camp.WHITE)) {
            return beforePosition.isSameRow(WHITE_PAWN_INITIAL_ROW);
        }
        return beforePosition.isSameRow(BLACK_PAWN_INITIAL_ROW);
    }

    @Override
    public boolean checkCanMoveByDistance(final Positions positions) {
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

    @Override
    public List<UnitDirectVector> getPossibleDirections() {
        if (getCamp() == Camp.BLACK) {
            return List.of(BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT);
        }
        return List.of(TOP, TOP_LEFT, TOP_RIGHT);
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
