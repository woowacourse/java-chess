package chess.piece;

import chess.chessboard.position.Direction;
import chess.game.Player;
import chess.chessboard.position.Position;

import java.util.*;
import java.util.stream.Collectors;

import static chess.chessboard.position.Direction.*;
import static chess.game.Player.BLACK;
import static chess.game.Player.NONE;

public final class Pawn extends Piece {

    private static final double SCORE = 1;

    public Pawn(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final Set<Position> positions = new HashSet<>();
        positions.addAll(findCatchPositions(source, board, getCatchingDirections()));
        positions.addAll(findMovedOnePosition(source, board, getForwardDirection()));
        positions.addAll(findMovedTwoPosition(source, board, getForwardDirection(), getForwardTwoDirection()));
        return positions.contains(target);
    }

    private List<Position> findCatchPositions(final Position source, final Map<Position, Piece> board, final List<Direction> directions) {
        return directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> board.get(position).isOpponent(player))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> findMovedOnePosition(final Position source, final Map<Position, Piece> board, final Direction direction) {
        final List<Direction> directions = List.of(direction);
        return directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> board.get(position).isSame(NONE))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> findMovedTwoPosition(final Position source,
                                                final Map<Position, Piece> board,
                                                final Direction forwardDirection,
                                                final Direction forwardTwoDirection) {
        if (!source.isInBoardAfterMoved(forwardTwoDirection)) {
            return List.of();
        }
        final Position oneForwardPosition = source.createMovablePosition(forwardDirection);
        final Position twoForwardPosition = source.createMovablePosition(forwardTwoDirection);
        if (source.isStartingPositionOfPawn() && isMovedTwoPosition(board.get(oneForwardPosition), board.get(twoForwardPosition))) {
            return List.of(twoForwardPosition);
        }
        return List.of();
    }

    private boolean isMovedTwoPosition(final Piece pieceInOneForwardPosition, final Piece pieceInTwoForwardPosition) {
        return pieceInOneForwardPosition.isSame(NONE) && !pieceInTwoForwardPosition.isSame(player);
    }

    private List<Direction> getCatchingDirections() {
        if (player.equals(BLACK)) {
            return List.of(SOUTHEAST, SOUTHWEST);
        }
        return List.of(NORTHEAST, NORTHWEST);
    }

    private Direction getForwardDirection() {
        if (player.equals(BLACK)) {
            return SOUTH;
        }
        return NORTH;
    }

    private Direction getForwardTwoDirection() {
        if (player.equals(BLACK)) {
            return BLACK_PAWN_FORWARD_TWO;
        }
        return WHITE_PAWN_FORWARD_TWO;
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
