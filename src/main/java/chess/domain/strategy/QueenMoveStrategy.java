package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        return isDiagonal(source, destination) || isStraight(source, destination);
    }

    private static boolean isDiagonal(Square source, Square destination) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);

        return squareDifferent.fileDiff() - squareDifferent.rankDiff() == 0;
    }

    private boolean isStraight(Square source, Square destination) {
        SquareDifferent diff = source.calculateDiff(destination);

        return diff.rankDiff() == 0 || diff.fileDiff() == 0;
    }
}
