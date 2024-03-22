package chess.model.position;

import chess.model.piece.Side;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Movement {
    private final int fileDifference;
    private final int rankDifference;

    public Movement(int fileDifference, int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
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

    public boolean isDiagonalMovement() {
        if (isNotMoved()) {
            return false;
        }
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public boolean isCrossMovement() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference == 0 || rankDifference == 0;
    }

    private boolean isNotMoved() {
        return fileDifference == 0 && rankDifference == 0;
    }

    public boolean hasSame(int displacement) {
        if (isCrossMovement() || isDiagonalMovement()) {
            return Math.abs(fileDifference) == displacement || Math.abs(rankDifference) == displacement;
        }
        return Math.abs(fileDifference) + Math.abs(rankDifference) == displacement;
    }

    public List<ChessPosition> findPath(ChessPosition source) {
        if (!isDiagonalMovement() && !isCrossMovement()) {
            return List.of();
        }
        int fileOffset = calculateIncrement(fileDifference);
        int rankOffset = calculateIncrement(rankDifference);
        int pathLength = calculatePathLength();

        return makePath(source, pathLength, fileOffset, rankOffset);
    }

    private int calculateIncrement(int difference) {
        return Integer.compare(difference, 0);
    }

    private int calculatePathLength() {
        if (fileDifference == 0) {
            return Math.abs(rankDifference);
        }
        return Math.abs(fileDifference);
    }

    private List<ChessPosition> makePath(ChessPosition source, int pathLength, int fileOffset, int rankOffset) {
        List<ChessPosition> path = new ArrayList<>();
        ChessPosition prevPosition = source;
        while (path.size() < pathLength) {
            File nextFile = prevPosition.findNextFile(fileOffset);
            Rank nextRank = prevPosition.findNextRank(rankOffset);
            prevPosition = new ChessPosition(nextFile, nextRank);
            path.add(prevPosition);
        }
        return unmodifiableList(path);
    }

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }
}
