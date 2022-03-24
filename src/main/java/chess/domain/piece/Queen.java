package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Queen extends Piece{
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance == 0) {
            return true;
        }
        if (rowDistance == 0) {
            return true;
        }
        return columnDistance == rowDistance;
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isQueen() {
        return true;
    }

    @Override
    public boolean isRook() {
        return false;
    }

}
