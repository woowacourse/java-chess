package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Square> map;

    public Board(final Map<Position, Square> map) {
        this.map = new TreeMap<>(map);
    }

    boolean isSameColor(final Position origin, Piece.Color color) {
        return map.get(origin).isSameColor(color);
    }

    boolean action(final Position origin, final Position target) {
        if (hasObstacle(origin, target)) {
            return false;
        }
        Square originSquare = map.get(origin);
        Square targetSquare = map.get(target);

        if (targetSquare.isEmpty()) {
            return isValidMove(originSquare, targetSquare);
        }
        return isValidAttack(originSquare, targetSquare);
    }

    private boolean isValidAttack(final Square origin, final Square target) {
        if (!origin.isValidAttack(target)) {
            return false;
        }
        origin.attackPiece(target);
        return true;
    }

    public boolean isValidMove(final Square origin, final Square target) {
        if (!origin.isValidMove(target)) {
            return false;
        }
        return origin.movePiece(target);
    }

    private boolean hasObstacle(final Position origin, final Position target) {
        for (Position route : origin.findRoutes(target)) {
            final boolean empty = map.get(route).isEmpty();
            if (!empty) {
                return true;
            }
        }
        return false;
    }

    ScoreCalculator createScoreCalculator() {
        List<Square> squares = new ArrayList<>(map.values());
        return new ScoreCalculator(squares);
    }

    List<Square> getSquaresExceptEmpty() {
        return map.values().stream()
                .filter(square -> !square.isEmpty())
                .collect(Collectors.toList());
    }

    List<Square> values() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Board board = (Board) o;
        return Objects.equals(map, board.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
