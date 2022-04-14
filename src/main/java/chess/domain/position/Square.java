package chess.domain.position;

import java.util.Locale;
import java.util.Objects;

public class Square {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Column column;
    private final Row row;

    public Square(String position) {
        this(Column.find(position.toLowerCase(Locale.ROOT).charAt(FILE_INDEX)),
                Row.find(position.toLowerCase(Locale.ROOT).charAt(RANK_INDEX)));
    }

    public Square(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public Movement getGap(Square target) {
        int gapOfFile = column.getGap(target.column);
        int gapOfRank = row.getGap(target.row);
        return new Movement(gapOfFile, gapOfRank);
    }

    public Square add(Movement movement) {
        return movement.add(column, row);
    }

    public boolean checkFile(Column column) {
        return this.column == column;
    }

    public String getName() {
        return column.name() + row.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square)o;
        return column == square.column && row == square.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

}
