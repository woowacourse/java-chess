package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.Set;

public final class BlackPawn extends Pawn {

    private static final Rank INIT_RANK = Rank.SEVEN;
    private static final double score = 1;

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    protected Set<Position> computePath(final Position source, final Position target) {
        if (!canPawnMove(source, target, DOWN)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }

        return generateTargetPath(source, target);
    }

    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    private Set<Position> generateTargetPath(final Position source, final Position target) {
        if (source.isSameFile(target)) {
            validateInitBlack(source, target);
            return source.computeCrossPath(target);
        }
        return source.computeDiagonalPath(target);
    }

    private void validateInitBlack(final Position source, final Position target) {
        if (Math.abs(source.rankSub(target)) == TWO_SQUARES && !source.isRank(INIT_RANK)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }
    @Override
    public double getScore(Color color) {
        if (color == this.color) {
            return score;
        }

        return 0;
    }
}
