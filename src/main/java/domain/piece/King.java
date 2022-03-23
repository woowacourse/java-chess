package domain.piece;

import domain.position.Position;

public final class King extends Piece {

    public King(final Player player) {
        super(player, Unit.King);
    }

    @Override
    public boolean availableMove(Position position, Position position1) {
        return false;
    }
}
