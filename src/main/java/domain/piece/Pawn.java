package domain.piece;

import domain.position.Position;

public final class Pawn extends Piece {

    public Pawn(final Player player) {
        super(player, Unit.Pawn);
    }

    @Override
    public boolean availableMove(Position position, Position position1) {
        return false;
    }
}
