package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.*;

public class King extends Piece {
    private static final Score SCORE = new Score(0);

    private King(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static King of(String name, boolean isBlack) {
        return new King(name, isBlack);
    }

    public static Map<Position, King> initialKingPieces() {
        return new HashMap<Position, King>() {{
            put(Position.of("e8"), King.of("K", true));
            put(Position.of("e1"), King.of("k", false));
        }};
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        List<Direction> directions = new ArrayList<>();
        directions.addAll(Direction.linearDirection());
        directions.addAll(Direction.diagonalDirection());

        return directions.stream()
                .map(direction -> start.move(direction))
                .filter(nextPosition -> nextPosition.equals(end))
                .filter(nextPosition -> checkPosition(board, nextPosition))
                .findAny()
                .isPresent();
    }

    private boolean checkPosition(Map<Position, Piece> board, Position nextPosition) {
        return !board.containsKey(nextPosition) || !this.isSameColor(board.get(nextPosition));
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
