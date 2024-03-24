package chess.model.position;

import chess.model.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class Distance {
    private final int fileDifference;
    private final int rankDifference;

    public Distance(int fileDifference, int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean hasSame(int displacement) {
        int fileDistance = Math.abs(fileDifference);
        int rankDistance = Math.abs(rankDifference);

        if (isCrossMovement() || isDiagonalMovement()) {
            return fileDistance == displacement || rankDistance == displacement;
        }
        return fileDistance + rankDistance == displacement;
    }

    public boolean isForward(Side side) {
        if (fileDifference != 0) {
            return false;
        }
        if (side.isUpperSide()) {
            return rankDifference < 0;
        }
        return rankDifference > 0;
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

    public List<ChessPosition> findPath(ChessPosition source) {
        if (!isCrossMovement() && !isDiagonalMovement()) {
            return List.of();
        }
        int fileOffset = calculateIncrement(fileDifference);
        int rankOffset = calculateIncrement(rankDifference);
        int repeatCount = calculateRepeatCount();

        List<ChessPosition> path = new ArrayList<>();
        addPath(source, repeatCount, fileOffset, rankOffset, path);
        return path;
    }

    private int calculateIncrement(int difference) {
        return Integer.compare(difference, 0);
    }

    private int calculateRepeatCount() {
        if (fileDifference == 0) {
            return Math.abs(rankDifference);
        }
        return Math.abs(fileDifference);
    }

    private void addPath(ChessPosition source, int repeatCount, int fileOffset, int rankOffset,
                         List<ChessPosition> path) {
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
