package chess.domain.routesearchstrategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

import java.util.Set;

@FunctionalInterface
public interface RouteSearchStrategy {
    Set<Coordinate> search(ChessBoard chessBoard, Piece sourcePiece, Coordinate sourceCoordinate);
}
