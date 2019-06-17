package chess.domain;

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
