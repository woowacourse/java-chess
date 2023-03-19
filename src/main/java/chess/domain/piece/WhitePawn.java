package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.Set;

public final class WhitePawn extends Pawn {

    private static final Rank INIT_RANK = Rank.TWO;

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (canPawnMove(source, target, UP)) {
            return generateTargetPath(source, target);
        }

        throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
    }

    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    private Set<Position> generateTargetPath(final Position source, final Position target) {
        if (source.isFileEquals(target)) {
            validateInitWhite(source, target);
            return source.computeCrossPath(target);
        }
        return source.computeDiagonalPath(target);
    }

    private void validateInitWhite(final Position source, final Position target) {
        if (Math.abs(source.rankSub(target)) == 2 && !source.isRank(INIT_RANK)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }
}
