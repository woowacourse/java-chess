package chess.model.position;

import chess.model.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class Distance {
    private final int fileDifference;
    private final int rankDifference;

    public Distance(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean hasSame(final int displacement) {
        final int fileDistance = Math.abs(fileDifference);
        final int rankDistance = Math.abs(rankDifference);

        if (isCrossMovement() || isDiagonalMovement()) {
            return fileDistance == displacement || rankDistance == displacement;
        }
        return fileDistance + rankDistance == displacement;
    }

    public boolean isForward(final Side side) {
        if (fileDifference != 0) {
            return false;
        }
        if (side.isBlack()) {
            return rankDifference < 0;
        }
        if (side.isWhite()) {
            return rankDifference > 0;
        }
        return false;
    }

    public boolean isCrossMovement() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference == 0 || rankDifference == 0;
    }

    public boolean isDiagonalMovement() {
        if (isNotMoved()) {
            return false;
        }
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public List<ChessPosition> findPath(final ChessPosition source) {
        if (!isCrossMovement() && !isDiagonalMovement()) {
            return List.of();
        }
        final int fileOffset = calculateIncrement(fileDifference);
        final int rankOffset = calculateIncrement(rankDifference);
        final int repeatCount = calculateRepeatCount();

        final List<ChessPosition> path = new ArrayList<>();
        addPath(source, repeatCount, fileOffset, rankOffset, path);
        return path;
    }

    private int calculateIncrement(final int difference) {
        return Integer.compare(difference, 0);
    }

    private int calculateRepeatCount() {
        if (fileDifference == 0) {
            return Math.abs(rankDifference);
        }
        return Math.abs(fileDifference);
    }

    private void addPath(
            final ChessPosition source,
            int repeatCount, final int fileOffset, final int rankOffset,
            final List<ChessPosition> path
    ) {
        ChessPosition prevPosition = source;
        while (repeatCount-- > 0) {
            File nextFile = prevPosition.findNextFile(fileOffset);
            Rank nextRank = prevPosition.findNextRank(rankOffset);
            prevPosition = new ChessPosition(nextFile, nextRank);
            path.add(prevPosition);
        }
    }

    private boolean isNotMoved() {
        return fileDifference == 0 && rankDifference == 0;
    }

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }
}
