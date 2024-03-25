package chess.domain.square;

import chess.domain.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record Square(File file, Rank rank) {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public boolean isStraight(final Square target) {
        return file.isSameFile(target.file) || rank.isSameRank(target.rank());
    }

    public boolean isDiagonal(final Square target) {
        return file.calculateDistance(target.file) == rank.calculateDistance(target.rank);
    }

    public boolean isWithinOneStep(final Square target) {
        return file.calculateDistance(target.file) <= ONE_STEP &&
                rank.calculateDistance(target.rank) <= ONE_STEP;
    }

    public boolean isStraightAndDiagonal(final Square target) {
        if (file.calculateDistance(target.file) == ONE_STEP
                && rank.calculateDistance(target.rank) == TWO_STEP) {
            return true;
        }
        return file.calculateDistance(target.file) == TWO_STEP
                && rank.calculateDistance(target.rank) == ONE_STEP;
    }

    public boolean isNotBackward(final Square target, final PieceColor color) {
        if (color.isBlack()) {
            return rank.calculateDirection(target.rank) > 0;
        }
        return rank.calculateDirection(target.rank) < 0;
    }

    public boolean isOnlyForward(final Square target) {
        if (isFirstMove()) {
            return rank.calculateDistance(target.rank) <= TWO_STEP
                    && file.isSameFile(target.file);
        }
        return rank.calculateDistance(target.rank) == ONE_STEP
                && file.isSameFile(target.file);
    }

    private boolean isFirstMove() {
        return rank.isSameRank(Rank.TWO) || rank.isSameRank(Rank.SEVEN);
    }

    public boolean isAttack(final Square target) {
        if (canAttack(target)) {
            return isDiagonal(target)
                    && file.calculateDistance(target.file) == 1;
        }
        return false;
    }

    private boolean canAttack(final Square target) {
        return target != null;
    }

    public List<Square> findPath(final Square target) {
        if (!(isStraight(target) || isDiagonal(target))) {
            return List.of();
        }

        final List<File> filePath = file.findFilePath(target.file);
        final List<Rank> rankPath = rank.findRankPath(target.rank);
        return createPath(filePath, rankPath);
    }

    private List<Square> createPath(final List<File> filePath, final List<Rank> rankPath) {
        if (filePath.isEmpty()) {
            return mapToPathList(rankPath, rank -> new Square(file, rank));
        }

        if (rankPath.isEmpty()) {
            return mapToPathList(filePath, file -> new Square(file, rank));
        }

        final List<Square> path = new ArrayList<>();
        for (int i = 0; i < filePath.size(); i++) {
            path.add(new Square(filePath.get(i), rankPath.get(i)));
        }
        return path;
    }

    private <T> List<Square> mapToPathList(final List<T> list, final Function<T, Square> mapper) {
        return list.stream()
                .map(mapper)
                .toList();
    }
}
