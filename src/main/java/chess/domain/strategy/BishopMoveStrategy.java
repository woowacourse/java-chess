package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);

        return Math.abs(squareDifferent.fileDiff()) - Math.abs(squareDifferent.rankDiff()) == 0;
    }
}
