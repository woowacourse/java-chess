package chess.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Square> map;

    public Board(final Map<Position, Square> map) {
        this.map = new HashMap<>(map);
    }

    public boolean action(final Position origin, final Position target) {
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

    public boolean isValidAttack(final Square origin, final Square target) {
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

    public ScoreCalculator createScoreCalculator() {
        List<Square> whites = new ArrayList<>();
        List<Square> blacks = new ArrayList<>();

        for (final Square square : map.values()) {
            if (square.isSameColor(Piece.Color.WHITE)) {
                whites.add(square);
            }
            if (square.isSameColor(Piece.Color.BLACK)) {
                blacks.add(square);
            }
        }
        return new ScoreCalculator(whites, blacks);
    }
}
