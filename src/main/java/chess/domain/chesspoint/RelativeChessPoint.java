package chess.domain.chesspoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RelativeChessPoint {
    private static Map<String, RelativeChessPoint> relativePointPool = new HashMap<>();
    private final ChessRow relativeRow;
    private final ChessColumn relativeColumn;

    private RelativeChessPoint(int relativeRow, int relativeColumn) {
        this.relativeRow = ChessRow.of(relativeRow);
        this.relativeColumn = ChessColumn.of(relativeColumn);
    }

    public static RelativeChessPoint of(int relativeRow, int relativeColumn) {
        String key = hashPoint(relativeRow, relativeColumn);
        if (relativePointPool.containsKey(key)) {
            return relativePointPool.get(key);
        }

        RelativeChessPoint newRelativeChessPoint = new RelativeChessPoint(relativeRow, relativeColumn);
        relativePointPool.put(key, newRelativeChessPoint);
        return newRelativeChessPoint;
    }

    private static String hashPoint(int row, int column) {
        return "" + row + column;
    }

    public RelativeChessPoint toUnit() {
        if (relativeRow.isZero() && relativeColumn.isZero()) {
            return this;
        }

        int positiveGcd = Math.abs(calcGCD(relativeRow.getRow(), relativeColumn.getColumn()));

        return divideBy(positiveGcd);
    }

    private int calcGCD(int a, int b) {
        return a == 0 ? b : calcGCD(b % a, a);
    }

    private RelativeChessPoint divideBy(int number) {
        return RelativeChessPoint.of(relativeRow.divide(number), relativeColumn.divide(number));
    }

    public static RelativeChessPoint calculateUnitDirection(ChessPoint source, ChessPoint target) {
        RelativeChessPoint relativePoint = target.minus(source);
        return relativePoint.toUnit();
    }

    public int getRelativeRow() {
        return relativeRow.getRow();
    }

    public int getRelativeColumn() {
        return relativeColumn.getColumn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RelativeChessPoint that = (RelativeChessPoint) o;
        return Objects.equals(relativeRow, that.relativeRow) &&
                Objects.equals(relativeColumn, that.relativeColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativeRow, relativeColumn);
    }
}
