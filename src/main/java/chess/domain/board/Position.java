package chess.domain.board;

import chess.domain.piece.TeamColor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final int POSITION_SIZE = 64;
    private static final String ERROR_POSITION_VALUE = "00";
    public static final Position ERROR = new Position(ERROR_POSITION_VALUE);
    private static final int ALPHA_POSITION = 0;
    private static final int NUMBER_POSITION = 1;
    private static final int ZERO = 0;
    private static final int ROW_AND_COLUMN = 2;
    private static final int MOVE_ONE_WEIGHT = 1;

    private static Map<String, Position> positions = new LinkedHashMap<>(POSITION_SIZE);

    static {
        for (int row = ChessBoard.ROW_FIRST; row <= ChessBoard.ROW_LAST; row++) {
            for (char column = ChessBoard.COLUMN_FIRST; column <= ChessBoard.COLUMN_LAST;
                column++) {
                String boardPosition = ChessBoard.createPiecePositionName(row, column);
                positions.put(boardPosition, new Position(boardPosition));
            }
        }
    }

    private final char alpha;
    private final int number;

    private Position(String boardPosition) {
        validateLength(boardPosition);
        this.alpha = boardPosition.charAt(ALPHA_POSITION);
        this.number = Character.getNumericValue(boardPosition.charAt(NUMBER_POSITION));
    }

    public static Position valueOf(String value) {
        if (positions.containsKey(value)) {
            return positions.get(value);
        }
        throw new IllegalArgumentException();
    }

    public boolean isFront(Position value, TeamColor team) {
        if (team == TeamColor.BLACK) {
            return number - value.number > ZERO;
        }
        return number - value.number < ZERO;
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

    private boolean horizontal(Position value) {
        return alpha != value.alpha && number == value.number;
    }

    private boolean vertical(Position value) {
        return alpha == value.alpha && number != value.number;
    }

    private void validateLength(String boardPosition) {
        if (boardPosition.length() != ROW_AND_COLUMN) {
            throw new IllegalArgumentException();
        }
    }

    public Position moveUp() {
        String next = this.alpha + String.valueOf(this.number + MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveDown() {
        String next = this.alpha + String.valueOf(this.number - MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeft() {
        String next = (char) (this.alpha - MOVE_ONE_WEIGHT) + String.valueOf(this.number);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRight() {
        String next = (char) (this.alpha + MOVE_ONE_WEIGHT) + String.valueOf(this.number);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightUp() {
        String next =
            (char) (this.alpha + MOVE_ONE_WEIGHT) + String.valueOf(this.number + MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveRightDown() {
        String next =
            (char) (this.alpha + MOVE_ONE_WEIGHT) + String.valueOf(this.number - MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftUp() {
        String next =
            (char) (this.alpha - MOVE_ONE_WEIGHT) + String.valueOf(this.number + MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }

    public Position moveLeftDown() {
        String next =
            (char) (this.alpha - MOVE_ONE_WEIGHT) + String.valueOf(this.number - MOVE_ONE_WEIGHT);
        if (positions.containsKey(next)) {
            return Position.valueOf(next);
        }
        return ERROR;
    }


    public boolean startLine(TeamColor color) {
        if (color == TeamColor.BLACK) {
            return this.number == ChessBoard.ROW_BLACK_PAWN_LINE;
        }
        return this.number == ChessBoard.ROW_WHITE_PAWN_LINE;
    }

    public Character getColumn() {
        return alpha;
    }


    @Override
    public String toString() {
        return "Position{" +
            "boardPosition='" + alpha + ", " + number + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return alpha == position.alpha &&
            number == position.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha, number);
    }
}