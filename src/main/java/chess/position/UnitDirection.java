package chess.position;

import chess.util.MathUtils;

public class UnitDirection {

    private final int fileDifference;
    private final int rankDifference;

    private UnitDirection(int fileDifference, int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public static UnitDirection differencesOf(int fileDifference, int rankDifference) {
        if (fileDifference == 0 && rankDifference == 0) {
            throw new IllegalArgumentException("단위 이동은 한 칸 이상 이동해야 합니다.");
        }
        if (fileDifference == 0 || rankDifference == 0) {
            return new UnitDirection(Integer.signum(fileDifference), Integer.signum(rankDifference));
        }
        int gcd = MathUtils.gcd(Math.abs(fileDifference), Math.abs(rankDifference));
        return new UnitDirection(fileDifference / gcd, rankDifference / gcd);
    }

    public Position nextPosition(Position position) {
        return position.createPositionByDifferencesOf(fileDifference, rankDifference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UnitDirection other = (UnitDirection) o;
        if (fileDifference != other.fileDifference) {
            return false;
        }
        return rankDifference == other.rankDifference;
    }

    @Override
    public int hashCode() {
        int result = fileDifference;
        result = 31 * result + rankDifference;
        return result;
    }
}
