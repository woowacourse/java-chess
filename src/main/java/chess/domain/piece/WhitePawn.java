package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.HashSet;
import java.util.Set;

public final class WhitePawn extends Pawn {

    private static final Rank INIT_RANK = Rank.TWO;
    private static final int UP = -1;

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
// target.rank 가 source.rank 보다 크고 기울기가 1 또는 file이 같다.
    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    private Set<Position> generateTargetPath(final Position source, final Position target) {
        Set<Position> targetPath = new HashSet<>();
        if (Math.abs(source.rankSub(target)) == 2) {
            validateInitWhite(source);
            targetPath.add(source.getUpStraight());
        }
        targetPath.add(target);
        return targetPath;
    }

    private void validateInitWhite(final Position source) {
        if (!source.isRank(INIT_RANK)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }
}
