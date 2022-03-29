package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import chess.direction.RookDirection;
import chess.direction.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.Direction.*;
import static chess.Player.*;

public class Rook extends Piece {

    private static final double SCORE = 5;

    public Rook(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove_2(Position source, Position target, Map<Position, Piece> board) {
        Route route = findRoute(source, target);
        List<Position> positions = new ArrayList<>();
        Position nowPosition = source.createPositionFrom(route);
        while (board.get(nowPosition).isSame(NONE) && nowPosition.canCreatePositionTo(route)) {
            positions.add(nowPosition);
            nowPosition = nowPosition.createPositionFrom(route);
        }
        positions.add(nowPosition);
        return positions.contains(target);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int subtractedRank = source.subtractRankFrom(target);
        final int subtractedFile = source.subtractFileFrom(target);
        return RookDirection.findRouteFrom(subtractedRank, subtractedFile);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : getDirection()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private List<Position> createMovablePositions(Direction direction, Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        if (!source.isInBoardAfterMoved(direction)) {
            return positions;
        }
        Position nextPosition = source.createMovablePosition(direction);
        while (board.get(nextPosition).isSame(NONE) && nextPosition.isInBoardAfterMoved(direction)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.createMovablePosition(direction);
        }
        if (!board.get(nextPosition).isSame(this.player)) {
            positions.add(nextPosition);
        }
        return positions;
    }

    @Override
    protected List<Direction> getDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
