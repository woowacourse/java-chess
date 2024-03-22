package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class QueenMoveStrategy implements MoveStrategy {

    private final PathFindStrategy pathFindStrategy;

    public QueenMoveStrategy() {
        this.pathFindStrategy = new PathFindStrategy();
    }

    @Override
    public boolean check(Square source, Square destination, Board board) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (!pathFindStrategy.check(source, destination, board)) {
            return false;
        }

        int rankDiff = Math.abs(diff.rankDiff());
        int fileDiff = Math.abs(diff.fileDiff());

        return isDiagonal(rankDiff, fileDiff) || isStraight(rankDiff, fileDiff);
    }

    private boolean isDiagonal(int rankDiff, int fileDiff) {
        return fileDiff - rankDiff == 0;
    }

    private boolean isStraight(int fileDiff, int rankDiff) {
        return rankDiff == 0 || fileDiff == 0;
    }
}
