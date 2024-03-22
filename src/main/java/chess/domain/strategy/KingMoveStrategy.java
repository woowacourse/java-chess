package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, Board board) {
        SquareDifferent diff = source.calculateDiff(destination);
        int fileDiff = Math.abs(diff.fileDiff());
        int rankDiff = Math.abs(diff.rankDiff());

        if (fileDiff + rankDiff == 1) {
            return true;
        }

        return fileDiff == 1 && rankDiff == 1;
    }
}
