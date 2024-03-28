package chess.domain.pieceInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File x;
    private final Rank y;

    private Position(final File x, final Rank y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final String position) {
        File x = File.valueByFileIndex(String.valueOf(position.charAt(0)));
        Rank y = Rank.valueByRank(String.valueOf(position.charAt(1)));

        return new Position(x, y);
    }

    public PositionDifference calculateDifference(final Position otherPosition) {
        int xDifference = this.x.ordinal() - otherPosition.x.ordinal();
        int yDifference = this.y.ordinal() - otherPosition.y.ordinal();

        return new PositionDifference(xDifference, yDifference);
    }

    public List<Position> getInternalPositions(final Position otherPosition) {
        List<Position> internalPositions = new ArrayList<>();
        int deltaX = otherPosition.x.getIndex() - this.x.getIndex();
        int deltaY = otherPosition.y.getIndex() - this.y.getIndex();
        int max = Math.max(Math.abs(deltaX), Math.abs(deltaY));

        for (int step = 1; step < max; step++) {
            internalPositions.add(new Position(
                    File.valueByIndex(x.getIndex() + step * (deltaX / max)),
                    Rank.valueByIndex(y.getIndex() + step * (deltaY / max))));
        }

        return internalPositions;
    }

    public List<Position> getVerticalInternalPositions() {
        return Arrays.stream(Rank.values())
                .filter(value -> value != y)
                .map(value -> new Position(x, value))
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getXPosition() {
        return x.getIndex() - 1;
    }

    public int getYPosition() {
        return y.getIndex() - 1;
    }
}
