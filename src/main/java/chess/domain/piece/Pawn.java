package chess.domain.piece;

import static chess.domain.MoveStrategy.BLACK_PAWN;
import static chess.domain.MoveStrategy.BLACK_PAWN_FIRST;
import static chess.domain.MoveStrategy.WHITE_PAWN;
import static chess.domain.MoveStrategy.WHITE_PAWN_FIRST;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends Piece {

    private static final int BLACK_INIT_RANK = 7;
    private static final int WHITE_INIT_RANK = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        if (team == Team.BLACK) {
            return isBlackMovable(source, target);
        }
        return isWhiteMovable(source, target);
    }

    private boolean isBlackMovable(Position source, Position target) {
        return BLACK_PAWN.isMovable(source, target)
                || (source.isSameRank(BLACK_INIT_RANK) && BLACK_PAWN_FIRST.isMovable(source, target));
    }

    private boolean isWhiteMovable(Position source, Position target) {
        return WHITE_PAWN.isMovable(source, target)
                || (source.isSameRank(WHITE_INIT_RANK) && WHITE_PAWN_FIRST.isMovable(source, target));
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
