package chess.domain.piece;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Team;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return MoveStrategy.KING.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }
}
