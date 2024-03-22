package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class BishopMoveStrategy implements MoveStrategy {

    private final PathFindStrategy pathFindStrategy;

    public BishopMoveStrategy() {
        this.pathFindStrategy = new PathFindStrategy();
    }

    @Override
    public boolean check(Square source, Square destination, Board board) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (!pathFindStrategy.check(source, destination, board)) {
            return false;
        }

        return Math.abs(diff.fileDiff()) - Math.abs(diff.rankDiff()) == 0;
    }
}
