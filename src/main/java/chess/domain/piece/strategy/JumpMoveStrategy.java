package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;

public abstract class JumpMoveStrategy extends BasicMoveStrategy {

    @Override
    protected void checkValidMove(Position source, Position target, Board board) {
        checkPositionsOnBoard(source, target);
        checkValidTargetPosition(source, target);
        checkIsNotSameTeam(source, target, board);
    }

    protected abstract void checkValidTargetPosition(Position source, Position target);
}
