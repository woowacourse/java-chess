package chess.domain.chesspoint;

import java.util.Objects;

public class RelativeChessPoint {
    private final int relativeRow;
    private final int relativeColumn;

    private RelativeChessPoint(int relativeRow, int relativeColumn) {
        this.relativeRow = relativeRow;
        this.relativeColumn = relativeColumn;
    }

    public static RelativeChessPoint of(int relativeRow, int relativeColumn) {
        return new RelativeChessPoint(relativeRow, relativeColumn);
    }

    public RelativeChessPoint toUnit() {
        if (relativeRow == 0 && relativeColumn == 0) {
            return this;
        }

        int positiveGcd = Math.abs(calcGCD(relativeRow, relativeColumn));

        return divideBy(positiveGcd);
    }

    private int calcGCD(int a, int b) {
        return a == 0 ? b : calcGCD(b % a, a);
    }

    private RelativeChessPoint divideBy(int number) {
        return RelativeChessPoint.of(relativeRow / number, relativeColumn / number);
    }

    public static RelativeChessPoint calculateUnitDirection(ChessPoint source, ChessPoint target) {
        RelativeChessPoint relativePoint = target.minus(source);
        return relativePoint.toUnit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RelativeChessPoint that = (RelativeChessPoint) o;
        return relativeRow == that.relativeRow &&
                relativeColumn == that.relativeColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativeRow, relativeColumn);
    }
}
