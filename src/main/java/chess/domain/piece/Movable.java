package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Square;

import java.util.Set;

@FunctionalInterface
public interface Movable {
    Set<Square> getCanMoveSquares(Square square, ChessBoard chessBoard);
}