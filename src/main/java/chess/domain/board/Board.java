package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Square, Piece> board;

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Optional<Piece> findPieceOf(final Square square) {
        return Optional.ofNullable(board.get(square));
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
                .anyMatch(square -> board.containsKey(square));
    }

    private boolean isAttackable(final Square source, final Square destination) {
        return board.containsKey(destination) && !isSameColor(source, destination);
    }

    private boolean isSameColor(final Square source, final Square destination) {
        return board.get(source).isBlack() == board.get(destination).isBlack();
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
        final Piece piece = board.get(source);
        final Square destination = routes.get(0);
        final int distanceRank = Math.abs(destination.calculateDistanceRank(source));
        return !board.containsKey(destination)
                && (distanceRank == 2 && source.isInitPawnPosition(piece.isBlack()) || (distanceRank == 1));
    }

    public void move(final Square source, final Square destination) {
        board.put(destination, board.remove(source));
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
