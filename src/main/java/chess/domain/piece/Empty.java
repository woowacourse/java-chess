package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Empty extends Piece {

    public Empty(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Piece pieceInTarget) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        throw new UnsupportedOperationException();
    }
}
