package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public final class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Color.BLACK);
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
        if (target.equals(source.getDownStraight().getDownStraight())) {
            validateInitBlack(source);
            targetPath.add(source.getDownStraight());
            targetPath.add(source.getDownStraight().getDownStraight());
        }
        targetPath.add(target);
        return targetPath;
    }

    private void validateInitBlack(final Position source) {
        if (!source.isBlackPawnInitRank()) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    private void validateIsMovable(final Position source, final Position target) {
        if (!source.canBlackPawnMove(target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }
}
