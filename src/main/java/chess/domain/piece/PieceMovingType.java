package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.routesearchstrategy.NonSlidingRouteSearchStrategy;
import chess.domain.routesearchstrategy.PawnRouteSearchStrategy;
import chess.domain.routesearchstrategy.RouteSearchStrategy;
import chess.domain.routesearchstrategy.SlidingRouteSearchStrategy;

import java.util.Set;

public enum PieceMovingType {
    SLIDING(SlidingRouteSearchStrategy::search),
    NON_SLIDING(NonSlidingRouteSearchStrategy::search),
    PAWN(PawnRouteSearchStrategy::search);
    
    private final RouteSearchStrategy routeSearchStrategy;
    
    PieceMovingType(RouteSearchStrategy routeSearchStrategy) {
        this.routeSearchStrategy = routeSearchStrategy;
    }
    
    public Set<Coordinate> movableRouteSearch(ChessBoard chessBoard, Piece sourcePiece, Coordinate sourceCoordinate) {
        return routeSearchStrategy.search(chessBoard, sourcePiece, sourceCoordinate);
    }
}
