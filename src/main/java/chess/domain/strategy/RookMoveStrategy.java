package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent diff = source.calculateDiff(destination);

        return diff.rankDiff() == 0 || diff.fileDiff() == 0;
    }
}
