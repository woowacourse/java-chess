package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.position.Move;

public class Pawn extends Piece {

    private static final int UNTOUCHED_PAWN_MOVE_SIZE = 2;

    private final boolean isTouched;

    public Pawn(Color color) {
        super(color);
        this.isTouched = false;
    }

    private Pawn(Color color, boolean isTouched) {
        super(color);
        this.isTouched = isTouched;
    }

    @Override
    public Piece touch() {
        if (!isTouched) {
            return createTouchedPawn();
        }
        return this;
    }

    private Pawn createTouchedPawn() {
        return new Pawn(color, true);
    }

    @Override
    public boolean isValidMove(Move move, MoveType moveType) {
        if (moveType == MoveType.ATTACK) {
            return move.isOneWayUnitDiagonal(isUpward());
        }
        if (!isTouched) {
            return move.isOneWayStraightUnderSize(isUpward(), UNTOUCHED_PAWN_MOVE_SIZE);
        }
        return move.isOneWayUnitStraight(isUpward());
    }

    private boolean isUpward() {
        return color == Color.WHITE;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
