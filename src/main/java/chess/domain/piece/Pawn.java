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
        Set<Position> movablePath = new HashSet<>();
        if (color.isBlack()) {
            validateIsMovableForBlack(source, target, movablePath);
            return generateTargetPathForBlack(source, target);
        }
        if (color.isWhite()) {
            validateIsMovableForWhite(source, target, movablePath);
            return generateTargetPathForWhite(source, target);
        }
        throw new IllegalArgumentException();
    }

    private Set<Position> generateTargetPathForBlack(final Position source, final Position target) {
        Set<Position> targetPath = new HashSet<>();
        if (target.equals(source.getDownStraight().getDownStraight())) {
            targetPath.add(source.getDownStraight());
            targetPath.add(source.getDownStraight().getDownStraight());
        }
        System.out.println(targetPath);
        targetPath.add(target);
        return targetPath;
    }

    private Set<Position> generateTargetPathForWhite(final Position source, final Position target) {
        Set<Position> targetPath = new HashSet<>();
        if (target.equals(source.getUpStraight().getUpStraight())) {
            targetPath.add(source.getUpStraight());
            targetPath.add(source.getUpStraight().getUpStraight());
        }
        targetPath.add(target);
        return targetPath;
    }

    private void validateIsMovableForWhite(final Position source, final Position target, final Set<Position> movablePath) {
        movablePath.add(source.getUpStraight());
        movablePath.add(source.getLeftUpDiagonal());
        movablePath.add(source.getRightUpDiagonal());
        if (source.isWhitePawnInitRank()) {
            movablePath.add(source.getUpStraight().getUpStraight());
        }
        if (!movablePath.contains(target)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateIsMovableForBlack(final Position source, final Position target, final Set<Position> movablePath) {
        movablePath.add(source.getDownStraight());
        movablePath.add(source.getLeftDownDiagonal());
        movablePath.add(source.getRightDownDiagonal());
        if (source.isBlackPawnInitRank()) {
            movablePath.add(source.getDownStraight().getDownStraight());
        }
        if (!movablePath.contains(target)) {
            throw new IllegalArgumentException();
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
}
