package chess.domain.position;

import chess.domain.pieceinformations.TeamColor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Position {
    public static final Position ERROR = new Position(AlphaColumns.ERROR, NumberRows.ERROR);
    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    static {
        for (NumberRows row : NumberRows.rows()) {
            for (AlphaColumns column : AlphaColumns.columns()) {
                final String position = column.alpha() + row.number();
                CACHE.put(position, new Position(position));
            }
        }
    }

    private final AlphaColumns column;
    private final NumberRows row;

    private Position(String boardPosition) {
        validateLength(boardPosition);
        this.column = AlphaColumns.getInstance(boardPosition.charAt(0));
        this.row = NumberRows.getInstance(boardPosition.charAt(1));
    }

    private Position(AlphaColumns alpha, NumberRows number) {
        this.column = alpha;
        this.row = number;
    }

    public static Position valueOf(String value) {
        if (CACHE.containsKey(value)) {
            return CACHE.get(value);
        }
        throw new IllegalArgumentException();
    }

    public static Position valueOf(AlphaColumns column, NumberRows row) {
        final String value = column.alpha() + row.number();
        if (CACHE.containsKey(value)) {
            return CACHE.get(value);
        }
        return ERROR;
    }

    public static List<Position> values() {
        return new ArrayList<>(CACHE.values());
    }

    private void validateLength(String boardPosition) {
        if (boardPosition.length() != 2) {
            throw new IllegalArgumentException("좌표의 문자길이는 2입니다.");
        }
    }

    public Position moveFront(TeamColor teamColor) {
        if (teamColor == TeamColor.BLACK) {
            return moveDown();
        }
        return moveUp();
    }

    public Position moveUp() {
        String next = column.alpha() + row.movedNumber(1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveDown() {
        String next = column.alpha() + row.movedNumber(-1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeft() {
        String next = column.movedAlpha(-1) + row.number();
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRight() {
        String next = column.movedAlpha(1) + row.number();
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightUp() {
        String next = column.movedAlpha(1) + row.movedNumber(1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightDown() {
        String next = column.movedAlpha(1) + row.movedNumber(-1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftUp() {
        String next = column.movedAlpha(-1) + row.movedNumber(1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftDown() {
        String next = column.movedAlpha(-1) + row.movedNumber(-1);
        if (CACHE.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public boolean pawnLine(TeamColor color) {
        if (color == TeamColor.BLACK) {
            return row.number().equals("7");
        }
        return row.number().equals("2");
    }

    public Character getColumn() {
        return column.alpha().charAt(0);
    }

    @Override
    public String toString() {
        return "Position{" +
            "boardPosition='" + column + ", " + row + '\'' +
            '}';
    }

}