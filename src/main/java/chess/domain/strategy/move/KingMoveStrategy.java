package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class KingMoveStrategy extends MoveStrategy {

    @Override
    public boolean movable(Position source, Position target, Board board) {
        if (isSamePosition(source, target)) {
            return false;
        }

        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return (fileGap <= 1 && rankGap <= 1)
                && checkTarget(source, target, board);
    }
}
