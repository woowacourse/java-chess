package chess.domain.piece;

import static chess.domain.MoveStrategy.BISHOP;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return BISHOP.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }
}
