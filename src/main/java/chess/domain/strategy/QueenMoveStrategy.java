package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);

        int rankDiff = Math.abs(squareDifferent.rankDiff());
        int fileDiff = Math.abs(squareDifferent.fileDiff());

        return isDiagonal(rankDiff, fileDiff) || isStraight(rankDiff, fileDiff);
    }

    private boolean isDiagonal(int rankDiff, int fileDiff) {
        return fileDiff - rankDiff == 0;
    }

    private boolean isStraight(int fileDiff, int rankDiff) {
        return rankDiff == 0 || fileDiff == 0;
    }
}
