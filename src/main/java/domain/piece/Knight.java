package domain.piece;

import domain.position.Position;

public final class Knight extends Piece {

    public Knight(final Player player) {
        super(player, Unit.Knight);
    }

    @Override
    public boolean availableMove(Position position, Position position1) {
        return false;
    }
}
