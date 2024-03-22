package chess.model.position;

import chess.model.piece.Side;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.util.Collections.unmodifiableList;

public class Movement {
    private static final int MAX_DIFFERENCE = Rank.length() - 1;

    private final int fileDifference;
    private final int rankDifference;

    public Movement(int fileDifference, int rankDifference) {
        validateDifference(fileDifference);
        validateDifference(rankDifference);
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    private void validateDifference(int difference) {
        if (abs(difference) > MAX_DIFFERENCE) {
            throw new IllegalArgumentException("File 혹은 Rank의 좌표차 절댓값은 " + MAX_DIFFERENCE + " 이하입니다.");
        }
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

    public boolean isDiagonal() {
        if (isNotMoved()) {
            return false;
        }
        return abs(fileDifference) == abs(rankDifference);
    }

    public boolean isOrthogonal() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference == 0 || rankDifference == 0;
    }

    private boolean isNotMoved() {
        return fileDifference == 0 && rankDifference == 0;
    }

    public boolean hasLengthOf(int displacement) {
        return calculateLength() == displacement;
    }

    private int calculateLength() {
        if (isNotMoved()) {
            return 0;
        }
        if (!isOrthogonal() && !isDiagonal()) {
            return abs(fileDifference) + abs(rankDifference);
        }
        if (fileDifference == 0) {
            return abs(rankDifference);
        }
        return abs(fileDifference);
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

    private int calculateIncrement(int difference) {
        return Integer.compare(difference, 0);
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
