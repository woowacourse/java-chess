package chess.domain.piece;

import static chess.domain.MoveStrategy.KING;

import chess.domain.Position;
import chess.domain.Team;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return KING.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }
}
