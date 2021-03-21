package chess.domain.position;

import java.util.List;

public class Difference {

    private final int horizontalDegree;
    private final int verticalDegree;

    public Difference(final int horizontalDegree, final int verticalDegree) {
        this.horizontalDegree = horizontalDegree;
        this.verticalDegree = verticalDegree;
    }

    public Difference(final List<Integer> positionDifference) {
        horizontalDegree = positionDifference.get(0);
        verticalDegree = positionDifference.get(1);
    }

    public boolean isSameAbsoluteValue() {
        return Math.abs(horizontalDegree) == Math.abs(verticalDegree);
    }

    public boolean hasZeroValue() {
        return horizontalDegree == 0 || verticalDegree == 0;
    }

    public boolean allZeroValue() {
        return horizontalDegree == 0 && verticalDegree == 0;
    }

    public int biggerAbsoluteValue() {
        return Math.max(Math.abs(horizontalDegree), Math.abs(verticalDegree));
    }

    public Difference makeUnitLength() {
        final int absoluteValue = biggerAbsoluteValue();
        return new Difference(horizontalDegree / biggerAbsoluteValue(), verticalDegree / biggerAbsoluteValue());
    }

    public int horizontalDegree() {
        return horizontalDegree;
    }

    public int verticalDegree() {
        return verticalDegree;
    }
}
