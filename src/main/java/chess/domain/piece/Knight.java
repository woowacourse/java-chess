package chess.domain.piece;

import static chess.domain.board.UnitDirectVector.BOTTOM_BOTTOM_LEFT;
import static chess.domain.board.UnitDirectVector.BOTTOM_BOTTOM_RIGHT;
import static chess.domain.board.UnitDirectVector.LEFT_LEFT_DOWN;
import static chess.domain.board.UnitDirectVector.LEFT_LEFT_TOP;
import static chess.domain.board.UnitDirectVector.RIGHT_RIGHT_DOWN;
import static chess.domain.board.UnitDirectVector.RIGHT_RIGHT_TOP;
import static chess.domain.board.UnitDirectVector.TOP_TOP_LEFT;
import static chess.domain.board.UnitDirectVector.TOP_TOP_RIGHT;
import static chess.domain.piece.PieceProperty.KNIGHT;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import java.util.List;
import java.util.function.Consumer;

public final class Knight extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int FIRST_MOVABLE_DISTANCE = 2;
    private static final int SECOND_MOVABLE_DISTANCE = 1;

    public Knight(Camp camp) {
        super(camp, KNIGHT);
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> movePiece) {
        if (!checkCanMoveByDistance(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public void move(final Positions positions,
                     final Consumer<Piece> movePiece) {
        if (!checkCanMoveByDistance(positions)) {
            throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
        }
        movePiece.accept(this);
    }

    @Override
    public boolean checkCanMoveByDistance(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (rowDistance == FIRST_MOVABLE_DISTANCE && columnDistance == SECOND_MOVABLE_DISTANCE) {
            return true;
        }
        return columnDistance == FIRST_MOVABLE_DISTANCE && rowDistance == SECOND_MOVABLE_DISTANCE;
    }

    @Override
    public boolean checkCanMoveByDistance(final Positions positions) {
        int columnDistance = positions.calculateColumnDistance();
        int rowDistance = positions.calculateRowDistance();
        if (rowDistance == FIRST_MOVABLE_DISTANCE && columnDistance == SECOND_MOVABLE_DISTANCE) {
            return true;
        }
        return columnDistance == FIRST_MOVABLE_DISTANCE && rowDistance == SECOND_MOVABLE_DISTANCE;
    }

    @Override
    public List<UnitDirectVector> getPossibleDirections() {
        return List.of(
            TOP_TOP_LEFT, TOP_TOP_RIGHT,
            BOTTOM_BOTTOM_LEFT, BOTTOM_BOTTOM_RIGHT,
            LEFT_LEFT_TOP, LEFT_LEFT_DOWN,
            RIGHT_RIGHT_TOP, RIGHT_RIGHT_DOWN
        );
    }
}
