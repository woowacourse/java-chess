package chess.domain.position;

import chess.domain.pieceinformations.TeamColor;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.List;

public class Position {
    private static final Table<AlphaColumns, NumberRows, Position> CACHED_TABLE
        = HashBasedTable.create();

    static {
        for (NumberRows row : NumberRows.values()) {
            for (AlphaColumns column : AlphaColumns.values()) {
                CACHED_TABLE.put(column, row, new Position(column, row));
            }
        }
    }

    private final AlphaColumns column;
    private final NumberRows row;

    private Position(AlphaColumns alpha, NumberRows number) {
        this.column = alpha;
        this.row = number;
    }

    public static Position valueOf(String value) {
        validateLength(value);
        final AlphaColumns alpha = AlphaColumns.getInstance(value.charAt(0));
        final NumberRows number = NumberRows.getInstance(value.charAt(1));
        if (CACHED_TABLE.contains(alpha, number)) {
            return CACHED_TABLE.get(alpha, number);
        }
        throw new IllegalArgumentException();
    }

    public static Position valueOf(AlphaColumns column, NumberRows row) {
        if (CACHED_TABLE.contains(column, row)) {
            return CACHED_TABLE.get(column, row);
        }
        return null;
    }

    public static List<Position> values() {
        return new ArrayList<>(CACHED_TABLE.values());
    }

    private static void validateLength(String boardPosition) {
        if (boardPosition.length() != 2) {
            throw new IllegalArgumentException("좌표의 문자길이는 2입니다.");
        }
    }

    public Position moveFront(TeamColor teamColor) {
        if (teamColor == TeamColor.BLACK) {
            return move(0, -1);
        }
        return move(0, 1);
    }

    public Position move(int alphaDirection, int numberDirection) {
        final AlphaColumns movedAlpha = column.movedAlpha(alphaDirection);
        final NumberRows movedNumber = row.movedNumber(numberDirection);

        if (CACHED_TABLE.contains(movedAlpha, movedNumber)) {
            return Position.valueOf(movedAlpha, movedNumber);
        }

        return null;
    }

    public boolean pawnLine(TeamColor color) {
        if (color == TeamColor.BLACK) {
            return row == NumberRows.SEVEN;
        }
        return row == NumberRows.TWO;
    }

    public Character getColumn() {
        return column.alpha().charAt(0);
    }

    public String position() {
        return column.alpha() + row.number();
    }

    @Override
    public String toString() {
        return "Position{" +
            "boardPosition='" + column + ", " + row + '\'' +
            '}';
    }

}