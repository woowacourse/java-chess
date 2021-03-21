package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {
    private static final Score SCORE = Score.of(2.5);

    private Knight(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Knight of(String name, boolean color) {
        return new Knight(name, color);
    }

    public static Map<Position, Knight> initialKnightPieces() {
        return new HashMap<Position, Knight>() {{
            put(Position.of("b8"), Knight.of("N", true));
            put(Position.of("g8"), Knight.of("N", true));
            put(Position.of("b1"), Knight.of("n", false));
            put(Position.of("g1"), Knight.of("n", false));
        }};
    }

    private boolean checkPosition(Map<Position, Piece> board, Position nextPosition) {
        return !board.containsKey(nextPosition) || !this.isSameColor(board.get(nextPosition));
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        List<Direction> directions = Direction.knightDirection();
        return directions.stream()
                .map(direction -> start.move(direction))
                .filter(nextPosition -> nextPosition.equals(end))
                .filter(nextPosition -> checkPosition(board, nextPosition))
                .findAny()
                .isPresent();
    }
}
