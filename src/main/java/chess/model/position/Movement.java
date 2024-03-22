package chess.model.position;

import chess.model.piece.Side;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Movement {
    private final Difference fileDifference;
    private final Difference rankDifference;

    public Movement(Difference fileDifference, Difference rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean isForward(Side side) {
        if (!fileDifference.isZero()) {
            return false;
        }
        if (side.isBlack()) {
            return rankDifference.isNegative();
        }
        return rankDifference.isPositive();
    }

    public boolean isDiagonal() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference.absoluteValue() == rankDifference.absoluteValue();
    }

    public boolean isOrthogonal() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference.isZero() || rankDifference.isZero();
    }

    private boolean isNotMoved() {
        return fileDifference.isZero() && rankDifference.isZero();
    }

    public boolean hasLengthOf(int displacement) {
        return calculateLength() == displacement;
    }

    private int calculateLength() {
        if (isNotMoved()) {
            return 0;
        }
        if (!isOrthogonal() && !isDiagonal()) {
            return fileDifference.plusByAbsoluteValue(rankDifference);
        }
        if (fileDifference.isZero()) {
            return rankDifference.absoluteValue();
        }
        return fileDifference.absoluteValue();
    }

    public List<ChessPosition> findStraightPath(ChessPosition source) {
        if (!isDiagonal() && !isOrthogonal()) {
            return List.of();
        }
        int fileOffset = calculateIncrement(fileDifference);
        int rankOffset = calculateIncrement(rankDifference);
        int pathLength = calculateLength();

        return makePath(source, pathLength, fileOffset, rankOffset);
    }

    private int calculateIncrement(Difference difference) {
        if (difference.isNegative()) {
            return -1;
        }
        if (difference.isZero()) {
            return 0;
        }
        return 1;
    }

    private List<ChessPosition> makePath(ChessPosition source, int pathLength, int fileOffset, int rankOffset) {
        List<ChessPosition> path = new ArrayList<>();
        ChessPosition prevPosition = source;
        while (path.size() < pathLength) {
            File nextFile = prevPosition.calculateNextFile(fileOffset);
            Rank nextRank = prevPosition.calculateNextRank(rankOffset);
            prevPosition = new ChessPosition(nextFile, nextRank);
            path.add(prevPosition);
        }
        return unmodifiableList(path);
    }
}
