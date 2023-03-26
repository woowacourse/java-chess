package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Pawn extends Piece {
    protected static final int PAWN_MAX_MOVE_COUNT = 1;
    protected static final int PAWN_FIRST_MAX_MOVE_COUNT = 2;
    private static final Double VALUE = 1.0;
    private static final Double VALUE_SAME_FILE = 0.5;
    private static final int PAWN_COUNT_CRITERION_FOR_SCORING = 1;

    Pawn(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    public static Double calculateScore(int count) {
        if (count == PAWN_COUNT_CRITERION_FOR_SCORING) {
            return VALUE;
        }
        return VALUE_SAME_FILE * count;
    }

    abstract boolean canAttack(Position source, Position target, Piece piece);

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public Double getValue() {
        return null;
    }
}
