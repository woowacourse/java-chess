package chess.domain.piece.move.knight;

import java.util.List;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;

public class KnightMovingStrategy implements MovingStrategy {

    @Override
    public boolean move(Route route, EmptyPoints ignored) {
        List<Point> candidates = KnightDirection.createNextPointCandidates(route.getSource());
        return candidates.contains(route.getDestination());
    }
}
