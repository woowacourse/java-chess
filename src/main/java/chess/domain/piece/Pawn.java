package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Pawn extends Piece {
    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        return false;
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
        return true;
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
