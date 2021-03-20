package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

public class Knight extends Piece{

    private static final int ABLE_DISTANCE_TO_MOVE = 1;
    private static final Knight BLACK_KNIGHT = new Knight(Owner.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Owner.WHITE);

    public static Knight getInstanceOf(Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KNIGHT;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KNIGHT;
        }

        throw new IllegalArgumentException("Invalid Knight");
    }

    public Knight(Owner owner) {
        super(owner, Direction.knightDirections());
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return true;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
