package chess.domain.square;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record Square(File file, Rank rank) {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public static Square of(final File file, final Rank rank) {
        return new Square(file, rank);
    }

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

    public boolean isOnlyForward(final Square target) {
        // TODO: 뒤로 가는 경우 ~ 블랙은 아래로만, 화이트는 위로만 전진 가능
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

        List<File> filePath = file.findFilePath(target.file);
        List<Rank> rankPath = rank.findRankPath(target.rank);
        return createPath(filePath, rankPath);
    }

    private List<Square> createPath(List<File> filePath, List<Rank> rankPath) {
        if (filePath.isEmpty()) {
            return mapToPathList(rankPath, rank -> Square.of(file, rank));
        }

        if (rankPath.isEmpty()) {
            return mapToPathList(filePath, file -> Square.of(file, rank));
        }

        List<Square> path = new ArrayList<>();
        for (int i = 0; i < filePath.size(); i++) {
            path.add(Square.of(filePath.get(i), rankPath.get(i)));
        }
        return path;
    }

    private <T> List<Square> mapToPathList(List<T> list, Function<T, Square> mapper) {
        return list.stream()
                .map(mapper)
                .toList();
    }
}
