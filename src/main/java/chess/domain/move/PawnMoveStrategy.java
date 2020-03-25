package chess.domain.move;

import chess.domain.piece.Team;
import chess.domain.position.Position;

public class PawnMoveStrategy extends MoveStrategy {
    private static final int WHITE_POSITION = 2;
    private static final int BLACK_POSITION = 7;

    private Team team;

    @Override
    public boolean movable(Position source, Position target) {
        if (isSamePosition(source, target)) {
            return false;
        }

        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return fileGap <= 1 && rankGap <= 1;
    }
}