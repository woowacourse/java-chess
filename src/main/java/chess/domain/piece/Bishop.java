package chess.domain.piece;

import static chess.domain.piece.PieceProperty.BISHOP;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.function.Consumer;

public final class Bishop extends NotNullPiece {

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
    public boolean canMove(final Positions positions) {
        int columnDistance = positions.calculateColumnDistance();
        int rowDistance = positions.calculateRowDistance();
        return columnDistance == rowDistance;
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        return canMove(new Positions(beforePosition, afterPosition));
    }
}
