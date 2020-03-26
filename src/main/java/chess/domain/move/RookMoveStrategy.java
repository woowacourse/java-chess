package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.move.direction.DirectionStrategy;
import chess.domain.position.Position;

import java.util.List;

public class RookMoveStrategy extends MoveStrategy {
    @Override
    public boolean movable(Position source, Position target, Board board) {
        if (isSamePosition(source, target)) {
            return false;
        }

        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return fileGap == 0 && rankGap > 0 && checkObstacle(source, target, board);
    }
}
