package chess.domain.piece.bishop;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

public abstract class Bishop extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    public Bishop(final Owner owner) {
        super(owner, new Score(3.0d), Direction.diagonalDirections());
    }

    public static Bishop getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackBishop.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteBishop.getInstance();
        }

        throw new IllegalArgumentException("Invalid Bishop");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
