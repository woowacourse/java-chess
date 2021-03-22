package chess.domain.position;

import chess.domain.pieceinformations.TeamColor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Position {
    public static final int POSITION_SIZE = 64;
    public static final Position ERROR = new Position(AlphaColumn.ERROR, NumberRow.ERROR);
    private static Map<String, Position> cache = new LinkedHashMap<>(POSITION_SIZE);

    static {
        for (NumberRow row : NumberRow.values()) {
            for (AlphaColumn column : AlphaColumn.values()) {
                final String position = column.alpha() + row.number();
                cache.put(position, new Position(position));
            }
        }
    }

    private AlphaColumn column;
    private NumberRow row;

    private Position(String boardPosition) {
        validateLength(boardPosition);
        this.column = AlphaColumn.valueOf(boardPosition.charAt(0));
        this.row = NumberRow.valueOf(boardPosition.charAt(1));
    }

    private void validateLength(String boardPosition) {
        if (boardPosition.length() != 2) {
            throw new IllegalArgumentException("좌표의 문자길이는 2입니다.");
        }
    }

    private Position(AlphaColumn alpha, NumberRow number) {
        this.column = alpha;
        this.row = number;
    }

    public static Position valueOf(String value) {
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        throw new IllegalArgumentException();
    }

    public static Position valueOf(AlphaColumn column, NumberRow row) {
        final String value = column.alpha() + row.number();
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        throw new IllegalArgumentException();
    }

    public static List<Position> values() {
        return new ArrayList<>(cache.values());
    }

    public Position moveFront(TeamColor teamColor) {
        if (teamColor == TeamColor.BLACK) {
            return moveDown();
        }
        return moveUp();
    }

    public Position moveUp() {
        String next = column.alpha() + row.movedNumber(1);
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveDown() {
        String next = column.alpha() + row.movedNumber(-1);
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeft() {
        String next = column.movedAlpha(-1) + row.number();
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRight() {
        String next = column.movedAlpha(1) + row.number();
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightUp() {
        String next = column.movedAlpha(1) + row.movedNumber(1);
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightDown() {
        String next = column.movedAlpha(1) + row.movedNumber(-1);
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftUp() {
        String next = column.movedAlpha(-1) + row.movedNumber(1);
        if (cache.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftDown() {
        String next = column.movedAlpha(-1) + row.movedNumber(-1);
        if (cache.containsKey(next)) {
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