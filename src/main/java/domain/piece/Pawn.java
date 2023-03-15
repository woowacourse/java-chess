package domain.piece;

import domain.position.Position;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int PAWN_MOVABLE_DISTANCE = 1;
    private static final int PAWN_FIRST_MOVABLE_DISTANCE = 2;
    private static final char BLACK_INITIAL_RANK = '7';
    private static final char WHITE_INITIAL_RANK = '2';

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (super.isBlack() && source.getRank() == BLACK_INITIAL_RANK) {
            return source.moveDown(source, PAWN_FIRST_MOVABLE_DISTANCE).equals(destination);
        }
        if (!super.isBlack() && source.getRank() == WHITE_INITIAL_RANK) {
            return source.moveUp(source, PAWN_FIRST_MOVABLE_DISTANCE).equals(destination);
        }
        if (super.isBlack()) {
            return source.moveDown(source, PAWN_MOVABLE_DISTANCE).equals(destination);
        }
        return source.moveUp(source, PAWN_MOVABLE_DISTANCE).equals(destination);
    }
}
