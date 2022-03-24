package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Rook extends Piece {

    public Rook(Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance == 0) {
            return true;
        }
        return rowDistance == 0;
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
        return false;
    }

    @Override
    public boolean isRook() {
        return true;
    }

}
