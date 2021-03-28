package domain.piece.objects;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

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

    @Override
    public boolean canMove(Map<Position, Piece> board, Position start, Position end) {
        List<Direction> directions = Direction.knightDirection();
        return directions.stream()
                .map(direction -> start.move(direction))
                .filter(nextPosition -> nextPosition.notEmptyPosition() && nextPosition.equals(end))
                .anyMatch(nextPosition -> isEmptyPiecePosition(board, nextPosition) || !this.isSameColor(board.get(nextPosition)));
    }
}
