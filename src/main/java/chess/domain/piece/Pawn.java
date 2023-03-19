package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (color.isBlack()) {
            validateIsMovableForBlack(source, target);
            return generateTargetPathForBlack(source, target);
        }
        if (color.isWhite()) {
            validateIsMovableForWhite(source, target);
            return generateTargetPathForWhite(source, target);
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    private Set<Position> generateTargetPathForBlack(final Position source, final Position target) {
        Set<Position> targetPath = new HashSet<>();
        if (target.equals(source.getDownStraight().getDownStraight())) {
            validateInitBlack(source);
            targetPath.add(source.getDownStraight());
            targetPath.add(source.getDownStraight().getDownStraight());
        }
        targetPath.add(target);
        return targetPath;
    }

    private Set<Position> generateTargetPathForWhite(final Position source, final Position target) {
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
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }
    }

    private void validateInitBlack(final Position source) {
        if (!source.isBlackPawnInitRank()) {
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }
    }

    private void validateIsMovableForWhite(final Position source, final Position target) {
        if (!source.canWhitePawnMove(target)) {
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }
    }

    private void validateIsMovableForBlack(final Position source, final Position target) {
        if (!source.canBlackPawnMove(target)) {
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        if (source.isFileEquals(target)) {
            return isEmptyPosition.keySet()
                    .stream()
                    .allMatch(isEmptyPosition::get);
        }
        return !isEmptyPosition.get(target);
    }

    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
