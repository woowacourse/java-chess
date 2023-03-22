package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardSnapShot {

    private final Map<Square, Color> board;

    private BoardSnapShot(final Map<Square, Color> board) {
        this.board = board;
    }

    public static BoardSnapShot from(final Map<Square, Piece> board) {
        final Map<Square, Color> snapShot = board.keySet().stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> board.get(key).getColor(),
                        (before, after) -> before
                ));
        return new BoardSnapShot(snapShot);
    }

    public boolean canMove(final Square source, final List<Square> routes) {
        final boolean isExistHurdle = isExistHurdle(routes.subList(0, routes.size() - 1));
        final Square destination = routes.get(routes.size() - 1);
        if (!board.containsKey(destination)) {
            return !isExistHurdle;
        }
        return isAttackable(source, destination);
    }

    private boolean isExistHurdle(final List<Square> squares) {
        return squares.stream()
                .anyMatch(board::containsKey);
    }

    private boolean isAttackable(final Square source, final Square destination) {
        return board.containsKey(destination) && !isSameColor(source, destination);
    }

    private boolean isSameColor(final Square source, final Square destination) {
        final Color sourceColor = board.get(source);
        final Color destinationColor = board.get(destination);
        return sourceColor.isSame(destinationColor);
    }

    public boolean canMovePawn(final Square source, final List<Square> routes) {
        if (isDiagonalUnit(source, routes.get(0))) {
            return isAttackable(source, routes.get(0));
        }
        return canMoveStraight(source, routes);
    }

    private boolean isDiagonalUnit(final Square source, final Square destination) {
        final int distanceFile = destination.calculateDistanceFile(source);
        final int distanceRank = destination.calculateDistanceRank(source);
        return Math.abs(distanceFile) == Math.abs(distanceRank) && Math.abs(distanceFile) == 1;
    }

    private boolean canMoveStraight(final Square source, final List<Square> routes) {
        final Color sourceColor = board.get(source);
        final Square destination = routes.get(0);
        final int distanceRank = Math.abs(destination.calculateDistanceRank(source));
        return !board.containsKey(destination)
                && (distanceRank == 1 || (distanceRank == 2 && source.isInitPawnPosition(sourceColor.isBlack())));
    }
}
