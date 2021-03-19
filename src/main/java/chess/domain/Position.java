package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Position {

    public static final int POSITION_SIZE = 64;
    public static final Position ERROR = new Position("00");
    private static Map<String, Position> positions = new LinkedHashMap<>(POSITION_SIZE);

    static {
        for (int j = 1; j <= 8; j++) {
            for (char i = 'a'; i <= 'h'; i++) {
                String boardPosition = "" + i + j;
                positions.put(boardPosition, new Position(boardPosition));
            }
        }
    }

    private final char alpha;
    private final int number;

    private Position(String boardPosition) {
        validateLength(boardPosition);
        this.alpha = boardPosition.charAt(0);
        this.number = Character.getNumericValue(boardPosition.charAt(1));
    }

    public static Position valueOf(String value) {
        if (positions.containsKey(value)) {
            return positions.get(value);
        }
        throw new IllegalArgumentException();
    }

    private static Position valueOf(char alpha, int number) {
        return valueOf("" + alpha + number);
    }

    public static Set<String> getPositionKeySet() {
        return positions.keySet();
    }

    public boolean isFront(Position value, TeamColor team) {
        if (team == TeamColor.BLACK) {
            return number - value.number > 0;
        }
        return number - value.number < 0;
    }

    public boolean isMoveAmount(Position position, int value) {
        return Math.abs(number - position.number) == 1 && alpha == position.alpha;
    }

    public boolean isCross(Position value) {
        return horizontal(value) || vertical(value);
    }

    public boolean isDiagonal(Position value) {
        return Math.abs(alpha - value.alpha) == Math.abs(number - value.number);
    }

    public boolean isDistanceOne(Position value) {
        return
            (int) Math.sqrt(Math.pow(alpha - value.alpha, 2) + Math.pow(number - value.number, 2))
                == 1;
    }

    public boolean isDistanceThree(Position value) {
        return Math.abs(value.alpha - this.alpha) + Math.abs(value.number - this.number) == 3;
    }

    private boolean horizontal(Position value) {
        return alpha != value.alpha && number == value.number;
    }

    private boolean vertical(Position value) {
        return alpha == value.alpha && number != value.number;
    }

    private void validateLength(String boardPosition) {
        if (boardPosition.length() != 2) {
            throw new IllegalArgumentException();
        }
    }

    public Position moveUp() {
        String next = (char) this.alpha + String.valueOf(this.number + 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveDown() {
        String next = (char) this.alpha + String.valueOf(this.number - 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeft() {
        String next = (char) (this.alpha - 1) + String.valueOf(this.number);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRight() {
        String next = (char) (this.alpha + 1) + String.valueOf(this.number);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightUp() {
        String next = (char) (this.alpha + 1) + String.valueOf(this.number + 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightDown() {
        String next = (char) (this.alpha + 1) + String.valueOf(this.number - 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftUp() {
        String next = (char) (this.alpha - 1) + String.valueOf(this.number + 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftDown() {
        String next = (char) (this.alpha - 1) + String.valueOf(this.number - 1);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }


    public boolean startLine(TeamColor color) {
        if (color == TeamColor.BLACK) {
            return this.number == 7;
        }
        return this.number == 2;
    }

    public int getColumn() {
        return number;
    }

    @Override
    public String toString() {
        return "Position{" +
            "boardPosition='" + alpha + ", " + number + '\'' +
            '}';
    }
}