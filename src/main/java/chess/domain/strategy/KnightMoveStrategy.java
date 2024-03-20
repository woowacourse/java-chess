package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class KnightMoveStrategy implements MoveStrategy {

    /**
     * 한 칸 전진 후 한 칸 대각선으로 이동할 수 있다.
     */
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
