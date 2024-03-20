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

        if (diff.rankDiff() == 2 && diff.fileDiff() == 1) {
            return true;
        }

        return diff.rankDiff() == 1 && diff.fileDiff() == 2;
    }
}
