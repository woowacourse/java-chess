package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public final class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        validateIsMovable(source, target);
        return generateTargetPath(source, target);
    }

    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    private Set<Position> generateTargetPath(final Position source, final Position target) {
        Set<Position> targetPath = new HashSet<>();
        if (target.equals(source.getUpStraight().getUpStraight())) {
            validateInitWhite(source);
            targetPath.add(source.getUpStraight());
            targetPath.add(source.getUpStraight().getUpStraight());
        }
        targetPath.add(target);
        return targetPath;
    }

    private void validateInitWhite(final Position source) {
        if (!source.isWhitePawnInitRank()) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    private void validateIsMovable(final Position source, final Position target) {
        if (!source.canWhitePawnMove(target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }
}
