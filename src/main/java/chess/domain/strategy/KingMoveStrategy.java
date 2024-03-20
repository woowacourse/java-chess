package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (diff.fileDiff() + diff.rankDiff() == 1) {
            return true;
        }

        return diff.fileDiff() == 1 && diff.rankDiff() == 1;
    }
}
