package chess.domain.routesearchstrategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SlidingRouteSearchStrategy {
    private SlidingRouteSearchStrategy() {
        throw new IllegalStateException("유틸성 클래스는 인스턴스를 생성할 수 없습니다.");
    }
    
    public static Set<Coordinate> search(ChessBoard chessBoard, Piece sourcePiece, Coordinate sourceCoordinate) {
        return sourcePiece.directions().stream()
                .map(direction -> perDirectionRouteSearch(chessBoard, sourcePiece, sourceCoordinate, direction))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }
    
    private static Set<Coordinate> perDirectionRouteSearch(
            ChessBoard chessBoard,
            Piece sourcePiece,
            Coordinate sourceCoordinate,
            Direction direction
    ) {
        Coordinate nextCoordinate = sourceCoordinate;
        Set<Coordinate> searchedRouteCoordinates = new HashSet<>();
        
        while (nextCoordinate.canMove(direction)) {
            nextCoordinate = nextCoordinate.nextCoordinate(direction);
            if (chessBoard.isAllyAtCoordinate(sourcePiece, nextCoordinate)) {
                break;
            }
            
            if (chessBoard.isEnemyAtCoordinate(sourcePiece, nextCoordinate)) {
                searchedRouteCoordinates.add(nextCoordinate);
                break;
            }
    
            searchedRouteCoordinates.add(nextCoordinate);
        }
        
        return searchedRouteCoordinates;
    }
}
