package domain.piece;

import domain.position.Position;

public final class Bishop extends Piece {

    public Bishop(final Player player) {
        super(player, Unit.Bishop);
    }

    @Override
    public boolean availableMove(Position position, Position position1) {
        return false;
    }
}
