package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import chess.direction.KingDirection;
import chess.direction.Route;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.Direction.*;

public class King extends Piece {

    private static final double SCORE = 0;

    public King(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove_2(Position source, Position target, Map<Position, Piece> board) {
        Route route = findRoute(source, target);
        Position position = source.createPositionFrom(route);
        return !board.get(position).isSame(this.player);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int subtractedRank = source.subtractRankFrom(target);
        final int subtractedFile = source.subtractFileFrom(target);
        return KingDirection.findRouteFrom(subtractedRank, subtractedFile);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> directions = getDirection();
        List<Position> positions = directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
