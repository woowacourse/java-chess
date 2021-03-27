package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Cross;
import chess.domain.piece.info.Diagonal;
import chess.domain.piece.info.Position;

public abstract class AllMoveStrategy implements MoveStrategy {
    public void validateCross(Position source, Position target, ChessBoard chessBoard) {
        Cross cross = Cross.findCrossByTwoPosition(source, target);
        chessBoard.hasPieceInPath(source, target, cross);
    }

    public void validateDiagonal(Position source, Position target, ChessBoard chessBoard) {
        Diagonal diagonal = Diagonal.findDiagonalByTwoPosition(source, target);
        chessBoard.hasPieceInPath(source, target, diagonal);
    }
}
