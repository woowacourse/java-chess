package chess.domain.piece;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Team;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return MoveStrategy.QUEEN.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }
}
