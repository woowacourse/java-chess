package chess.domain.routesearchstrategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class NonSlidingRouteSearchStrategy {
    private NonSlidingRouteSearchStrategy() {
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
        if (isNonSlidingPieceImmovable(chessBoard, sourcePiece, sourceCoordinate, direction)) {
            return Collections.emptySet();
        }
    
        return Set.of(nextCoordinate(sourceCoordinate, direction));
    }
    
    private static boolean isNonSlidingPieceImmovable(
            ChessBoard chessBoard,
            Piece sourcePiece,
            Coordinate sourceCoordinate,
            Direction direction
    ) {
        return !sourceCoordinate.canMove(direction) ||
                chessBoard.isAllyAtCoordinate(sourcePiece, nextCoordinate(sourceCoordinate, direction));
    }
    
    private static Coordinate nextCoordinate(Coordinate sourceCoordinate, Direction direction) {
        return sourceCoordinate.nextCoordinate(direction);
    }
}
