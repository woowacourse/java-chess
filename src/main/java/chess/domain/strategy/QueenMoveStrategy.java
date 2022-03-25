package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Position;

public final class QueenMoveStrategy extends CommonMovingStrategy {

    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        checkCommonCondition(source, target, chessBoard);
    }
}
