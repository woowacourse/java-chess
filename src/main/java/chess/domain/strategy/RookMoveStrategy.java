package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent diff = source.calculateDiff(destination);

        return Math.abs(diff.rankDiff()) == 0 || Math.abs(diff.fileDiff()) == 0;
    }
}
