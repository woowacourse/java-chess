package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent diff = source.calculateDiff(destination);

        int rankDiff = Math.abs(diff.rankDiff());
        int fileDiff = Math.abs(diff.fileDiff());

        if (rankDiff == 2 && fileDiff == 1) {
            return true;
        }

        return rankDiff == 1 && fileDiff == 2;
    }
}
