package domain.piece;

import domain.position.Position;

public final class Queen extends Piece {

    public Queen(final Player player) {
        super(player, Unit.Queen);
    }

    @Override
    public boolean availableMove(Position position, Position position1) {
        return false;
    }
}
