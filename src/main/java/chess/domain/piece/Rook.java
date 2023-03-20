package chess.domain.piece;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Team;

public class Rook extends Piece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return MoveStrategy.ROOK.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff + rankDiff);
    }
}
