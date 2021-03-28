package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Position;

public class RookMoveStrategy extends AllMoveStrategy {
    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        validateCross(source, target, chessBoard);
        return true;
    }
}
