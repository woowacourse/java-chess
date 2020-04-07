package chess.domain.coordinate;

import java.util.Objects;

public class Vector {
    private final int fileVariation;
    private final int rankVariation;

    public Vector(final int fileVariation, final int rankVariation) {
        this.fileVariation = fileVariation;
        this.rankVariation = rankVariation;
    }

    public int sumOfAbsolute() {
        return Math.abs(fileVariation) + Math.abs(rankVariation);
    }

    public int subtractOfAbsolute() {
        return Math.abs(Math.abs(fileVariation) - Math.abs(rankVariation));
    }

    public boolean isDiagonal() {
        return Math.abs(fileVariation) == Math.abs(rankVariation);
    }

    public boolean isRangeUnderAbsolute(int value) {
        return Math.abs(fileVariation) <= value && Math.abs(rankVariation) <= value;
    }

    public boolean isStraight() {
        if (fileVariation == 0 && rankVariation == 0) {
            return false;
        }
        return fileVariation == 0 || rankVariation == 0;
    }

    public Direction getUnitVector() {
        int maxValue = getMaxValue();
        return Direction.findByValue(fileVariation / maxValue, rankVariation / maxValue);
    }

    public int getMaxValue() {
        return Math.max(Math.abs(fileVariation), Math.abs(rankVariation));
    }

    public int getRankVariation() {
        return rankVariation;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vector vector = (Vector) o;
        return fileVariation == vector.fileVariation &&
                rankVariation == vector.rankVariation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileVariation, rankVariation);
    }
}
