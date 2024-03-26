package chess.domain.square;

import chess.domain.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record Square(File file, Rank rank) {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public boolean isStraight(final Square target) {
        return isVertical(target) || isHorizontal(target);
    }

    private boolean isVertical(final Square target) {
        return file.isSameFile(target.file);
    }

    private boolean isHorizontal(final Square target) {
        return rank.isSameRank(target.rank);
    }

    public boolean isDiagonal(final Square target) {
        return file.calculateDistance(target.file) == rank.calculateDistance(target.rank);
    }

    public boolean isWithinOneStep(final Square target) {
        return isFileWithinOneStep(target) && isRankWithinOneStep(target);
    }

    private boolean isFileWithinOneStep(final Square target) {
        return file.calculateDistance(target.file) <= ONE_STEP;
    }

    private boolean isRankWithinOneStep(final Square target) {
        return rank.calculateDistance(target.rank) <= ONE_STEP;
    }

    public boolean isStraightAndDiagonal(final Square target) {
        if (isFileOneStep(target) && isRankTwoStep(target)) {
            return true;
        }
        return isFileTwoStep(target) && isRankOneStep(target);
    }

    private boolean isFileOneStep(final Square target) {
        return file.calculateDistance(target.file) == ONE_STEP;
    }

    private boolean isFileTwoStep(final Square target) {
        return file.calculateDistance(target.file) == TWO_STEP;
    }

    private boolean isRankOneStep(final Square target) {
        return rank.calculateDistance(target.rank) == ONE_STEP;
    }

    private boolean isRankTwoStep(final Square target) {
        return rank.calculateDistance(target.rank) == TWO_STEP;
    }

    public boolean isNotBackward(final Square target, final PieceColor color) {
        if (color.isBlack()) {
            return rank.calculateDirection(target.rank) > 0;
        }
        return rank.calculateDirection(target.rank) < 0;
    }

    public boolean isOnlyForward(final Square target) {
        if (isFirstMove()) {
            return isForwardWithinTwoStep(target) && isVertical(target);
        }
        return isForwardOnlyOneStep(target) && isVertical(target);
    }

    private boolean isForwardWithinTwoStep(final Square target) {
        return rank.calculateDistance(target.rank) <= TWO_STEP;
    }

    private boolean isForwardOnlyOneStep(final Square target) {
        return rank.calculateDistance(target.rank) == ONE_STEP;
    }

    private boolean isFirstMove() {
        return isWhiteFirstRank() || isBlackFirstRank();
    }

    private boolean isWhiteFirstRank() {
        return rank.isSameRank(Rank.TWO);
    }

    private boolean isBlackFirstRank() {
        return rank.isSameRank(Rank.SEVEN);
    }

    public boolean isAttack(final Square target) {
        return isDiagonal(target) && isFileOneStep(target);
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
