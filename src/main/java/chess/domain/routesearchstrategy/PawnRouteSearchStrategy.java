package chess.domain.routesearchstrategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnRouteSearchStrategy {
    private PawnRouteSearchStrategy() {
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
        if (canMove(chessBoard, sourcePiece, sourceCoordinate, direction)) {
            return Set.of(nextCoordinate(sourceCoordinate, direction));
        }
        
        return Collections.emptySet();
    }
    
    private static boolean canMove(ChessBoard chessBoard, Piece sourcePiece, Coordinate sourceCoordinate, Direction direction) {
        return sourceCoordinate.canMove(direction) &&
                (canMoveTwoForward(chessBoard, sourceCoordinate, direction) ||
                canMoveDiagonal(direction, chessBoard, sourcePiece, nextCoordinate(sourceCoordinate, direction)) ||
                canMoveOneForward(chessBoard, direction, nextCoordinate(sourceCoordinate, direction)));
    }
    
    private static boolean canMoveTwoForward(ChessBoard chessBoard, Coordinate sourceCoordinate, Direction direction) {
        return direction.isAbsoluteRowDirectionTwo() &&
                chessBoard.isEmpty(betweenRowCoordinate(sourceCoordinate, direction)) &&
                chessBoard.isEmpty(nextCoordinate(sourceCoordinate, direction));
    }
    
    private static Coordinate betweenRowCoordinate(Coordinate sourceCoordinate, Direction direction) {
        return sourceCoordinate.betweenRow(direction.rowDirection());
    }
    
    private static Coordinate nextCoordinate(Coordinate sourceCoordinate, Direction direction) {
        return sourceCoordinate.nextCoordinate(direction);
    }
    
    private static boolean canMoveDiagonal(Direction direction, ChessBoard chessBoard, Piece sourcePiece, Coordinate nextCoordinate) {
        return direction.isDiagonal() &&
                chessBoard.isEnemyAtCoordinate(sourcePiece, nextCoordinate);
    }
    
    private static boolean canMoveOneForward(ChessBoard chessBoard, Direction direction, Coordinate nextCoordinate) {
        return direction.isStraight() && chessBoard.isEmpty(nextCoordinate);
    }
}
