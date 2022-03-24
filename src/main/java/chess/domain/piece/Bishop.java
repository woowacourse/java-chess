package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Bishop extends Piece {

    public Bishop(Camp camp) {
        super(camp);
    }

    public boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        return columnDistance == rowDistance;
    }

    @Override
    public boolean isBishop() {
        return true;
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
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

}
