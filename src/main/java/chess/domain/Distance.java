package chess.domain;

public class Distance {
    private final int fileDistance;
    private final int rankDistance;

    public Distance(int fileDistance, int rankDistance) {
        this.fileDistance = Math.abs(fileDistance);
        this.rankDistance = Math.abs(rankDistance);
    }

    public boolean isDiagonal() {
        return fileDistance == rankDistance;
    }

    public boolean isHorizontal() {
        return fileDistance == 0;
    }

    public boolean isVertical() {
        return rankDistance == 0;
    }

    public boolean isMoveOneSpace() {
        boolean horizontal = isHorizontal() && rankDistance == 1;
        boolean vertical = isVertical() && fileDistance == 1;
        boolean diagonal = isDiagonal() && (fileDistance + rankDistance) == 2;

        return horizontal || vertical || diagonal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Distance distance = (Distance) o;

        if (fileDistance != distance.fileDistance) {
            return false;
        }
        return rankDistance == distance.rankDistance;
    }

    @Override
    public int hashCode() {
        int result = fileDistance;
        result = 31 * result + rankDistance;
        return result;
    }
}
