package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Position;

public class BishopMoveStrategy extends AllMoveStrategy {
    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        validateDiagonal(source, target, chessBoard);
        return true;
    }
}
