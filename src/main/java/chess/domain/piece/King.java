package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final King BLACK_KING = new King(Owner.BLACK);
    private static final King WHITE_KING = new King(Owner.WHITE);

    public King(Owner owner) {
        super(owner, new Score(0.0d), Direction.allDirections());
    }

    public static King getInstanceOf(Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KING;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid King");
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}