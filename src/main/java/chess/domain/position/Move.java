package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Move {

    private final int deltaFile;
    private final int deltaRank;

    public Move(int deltaFile, int deltaRank) {
        validateZero(deltaFile, deltaRank);
        this.deltaFile = deltaFile;
        this.deltaRank = deltaRank;
    }

    public Move(Position source, Position target) {
        this(Position.getDeltaFile(source, target), Position.getDeltaRank(source, target));
    }

    private void validateZero(int deltaFile, int deltaRank) {
        if (isZero(deltaFile, deltaRank)) {
            throw new IllegalArgumentException("움직임이 없습니다.");
        }
    }

    private boolean isZero(int deltaFile, int deltaRank) {
        return deltaFile == 0 && deltaRank == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(deltaFile) == Math.abs(deltaRank);
    }

    public boolean isStraight() {
        return deltaFile * deltaRank == 0;
    }

    public boolean isUnitDiagonal() {
        return isDiagonal() && getProductOfChange() == 1;
    }

    private int getProductOfChange() {
        return Math.abs(deltaFile) * Math.abs(deltaRank);
    }

    public boolean isUnitStraight() {
        return isStraight() && getSumOfChange() == 1;
    }

    private int getSumOfChange() {
        return Math.abs(deltaFile) + Math.abs(deltaRank);
    }

    public boolean isOneWayUnitDiagonal(boolean isUpward) {
        return isOneWay(isUpward, this::isUnitDiagonal);
    }

    public boolean isOneWayUnitStraight(boolean isUpward) {
        return isOneWay(isUpward, this::isUnitStraight);
    }

    public boolean isOneWayStraightUnderSize(boolean isUpward, int moveSize) {
        return isOneWay(isUpward, () -> isStraight() && Math.abs(deltaRank) <= moveSize);
    }

    private boolean isOneWay(boolean isUpward, Supplier<Boolean> filter) {
        if (!filter.get()) {
            return false;
        }
        if (isUpward) {
            return deltaRank > 0;
        }
        return deltaRank < 0;
    }

    public boolean isRightProductOfChange(int productOfChange) {
        return getProductOfChange() == productOfChange;
    }

    public List<Move> findRoute() {
        if (!(isStraight() || isDiagonal())) {
            return Collections.emptyList();
        }
        int unit = Math.max(Math.abs(deltaFile), Math.abs(deltaRank));
        Move unitMove = divide(unit);
        List<Move> route = new ArrayList<>();
        for (int i = 1; i < unit; i++) {
            route.add(unitMove.multiply(i));
        }
        return route;
    }

    private Move divide(int n) {
        return new Move(deltaFile / n, deltaRank / n);
    }

    private Move multiply(int n) {
        return new Move(deltaFile * n, deltaRank * n);
    }

    int getDeltaFile() {
        return deltaFile;
    }

    int getDeltaRank() {
        return deltaRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Move move = (Move) o;

        if (deltaFile != move.deltaFile) {
            return false;
        }
        return deltaRank == move.deltaRank;
    }

    @Override
    public int hashCode() {
        int result = deltaFile;
        result = 31 * result + deltaRank;
        return result;
    }
}
