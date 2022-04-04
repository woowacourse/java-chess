package chess.domain.piece;

import static chess.domain.board.UnitDirectVector.BOTTOM;
import static chess.domain.board.UnitDirectVector.BOTTOM_LEFT;
import static chess.domain.board.UnitDirectVector.BOTTOM_RIGHT;
import static chess.domain.board.UnitDirectVector.LEFT;
import static chess.domain.board.UnitDirectVector.RIGHT;
import static chess.domain.board.UnitDirectVector.TOP;
import static chess.domain.board.UnitDirectVector.TOP_LEFT;
import static chess.domain.board.UnitDirectVector.TOP_RIGHT;
import static chess.domain.piece.PieceProperty.QUEEN;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import java.util.List;
import java.util.function.Consumer;

public final class Queen extends NotNullPiece {

    private static final String NOT_MOVABLE_POSITION = "이동할 수 없는 위치입니다.";
    private static final int NOT_MOVED_DISTANCE = 0;

    public Queen(Camp camp) {
        super(camp, QUEEN);
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

    @Override
    public boolean checkCanMoveByDistance(final Positions positions) {
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
    public List<UnitDirectVector> getPossibleDirections() {
        return List.of(
            TOP, BOTTOM, RIGHT, LEFT,
            TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        );
    }
}
