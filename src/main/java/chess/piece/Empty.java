package chess.piece;

import chess.Position;
import chess.Team;

import java.util.List;

public class Empty extends Piece{

    private static final String NAME = ".";

    public Empty(Position position) {
        super(position, Team.NONE);
    }

    @Override
    public boolean isMovable(Position position) {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        return null;
    }

    @Override
    public boolean isKill(Piece piece) {
        return false;
    }
}
