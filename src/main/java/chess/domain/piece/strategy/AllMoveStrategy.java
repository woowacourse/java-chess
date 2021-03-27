package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Cross;
import chess.domain.piece.info.Diagonal;
import chess.domain.piece.info.Direction;
import chess.domain.piece.info.Position;

public abstract class AllMoveStrategy implements MoveStrategy {
    public void validateCross(Position source, Position target, ChessBoard chessBoard) {
        Direction cross = Cross.findCrossByTwoPosition(source, target);
        chessBoard.hasPieceInPath(source, target, cross);
    }

    public void validateDiagonal(Position source, Position target, ChessBoard chessBoard) {
        Direction diagonal = Diagonal.findDiagonalByTwoPosition(source, target);
        chessBoard.hasPieceInPath(source, target, diagonal);
    }
}
