package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class BishopMoveStrategy implements MoveStrategy {

    /**
     * 방해물이 없는 한 대각선으로 계속 뻗어나갈 수 있다.
     */
    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);

        return Math.abs(squareDifferent.fileDiff()) - Math.abs(squareDifferent.rankDiff()) == 0;
    }
}
