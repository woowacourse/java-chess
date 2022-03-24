package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Knight extends Piece{
    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance == 1 && rowDistance == 2) {
            return true;
        }
        return columnDistance == 2 && rowDistance == 1;
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
        return true;
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
