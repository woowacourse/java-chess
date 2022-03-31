package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LinearMovePiece extends Piece {
    public LinearMovePiece(Color color) {
        super(color);
    }

    protected List<Position> findLinearRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateMaxLinearLengthTo(target);
        int xChangeRatio = source.calculateXSlope(target, routeLength);
        int yChangeRatio = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacedOf(xChangeRatio * step, yChangeRatio * step);
            route.add(routeNode);
        }
        return route;
    }

    @Override
    protected abstract String baseSignature();

    @Override
    public abstract boolean isMovable(Map<Position, Piece> board, Position source, Position target);

    @Override
    public abstract List<Position> findRoute(Position source, Position target);

    @Override
    public abstract double score();
}
